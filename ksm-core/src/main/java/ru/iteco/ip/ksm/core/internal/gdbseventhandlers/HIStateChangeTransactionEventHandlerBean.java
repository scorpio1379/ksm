package ru.iteco.ip.ksm.core.internal.gdbseventhandlers;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.traversal.BranchOrderingPolicies;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.graphdb.traversal.Uniqueness;
import ru.iteco.ip.ksm.core.internal.gdbseventhandlers.abstracts.BaseIndicatorStateChangeTransactionEventHandler;
import ru.iteco.ip.ksm.core.internal.gdbspathexpanders.CalculateKPIStatePathExpader;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMHI;
import ru.iteco.ip.ksm.core.internal.statechangeevents.IndicatorStateChangeEventMessage;
import ru.iteco.ip.ksm.core.internal.statechangeevents.impl.HIStateChangeEventMessageImpl;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.shared.configmanagers.KSMConfigurationManager;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.KSMCoreApplicationConfig;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Scorpio on 29.09.2017.
 */
@Stateless(name = "HIStateChangeTransactionEventHandlerEJB")
@Default
public class HIStateChangeTransactionEventHandlerBean extends BaseIndicatorStateChangeTransactionEventHandler<InternalKSMHI> implements HIStateChangeTransactionEventHandler{
    /* TODO: Разработать асинхронные методы создания и отсылки сообщений об изменении статусов индикаторов для уменьшения времени обсчета */

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:/jms/topic/IndicatorStateChangeBus")
    private Topic indicatorStateChangeBus;

    /*TODO: КОСТЫЛЬ!!!!! нужна зависимость от ломенного класса CI из ksmObjectApi*/
    private static final RelationshipType ci2ciRelationshipType = () -> "LinkedCI";

    @Inject
    private JMSContext context;

    private KSMCoreApplicationConfig ksmCoreApplicationConfig;
    private CalculateKPIStatePathExpader<?> calculateKPIStatePathExpader;


    public HIStateChangeTransactionEventHandlerBean() {
    }

    @Inject
    public HIStateChangeTransactionEventHandlerBean(@Default KSMConfigurationManager ksmConfigurationManager , @Default CalculateKPIStatePathExpader<?> calculateKPIStatePathExpader) {
        this.ksmCoreApplicationConfig = ksmConfigurationManager.getKSMCoreApplicationConfig();
        this.calculateKPIStatePathExpader = calculateKPIStatePathExpader;
    }

    @Override
    public InternalKSMHI beforeCommit(TransactionData transactionData) throws Exception {
        return null;
    }

    @Override
    public void afterCommit(TransactionData transactionData, InternalKSMHI t) {
        Map<String, Object> md = transactionData.metaData();
        HashMap<Node, IndicatorStateChangeEventMessage<InternalKSMHI>> indicatorStateChangeEventMessageHashMap = new HashMap<>();
        if(transactionData.assignedNodeProperties().iterator().hasNext()){
            /*TODO: провериьт что будеть если getKsmObjIdFromGDBSNode или indicatorType вернут Exeption*/
            transactionData.assignedNodeProperties().forEach( propertyEntry -> {
                String ksmObjId = getKsmObjIdFromGDBSNode(propertyEntry.entity());
                String indicatorType = getIndicatorTypeFromGDBSNode(propertyEntry.entity());
                if ((ksmObjId != null) && (indicatorType.equalsIgnoreCase(KSMIndicatorType.HI.name()))) {

                    if (propertyEntry.key().equalsIgnoreCase("value")) {
                        indicatorStateChangeEventMessageHashMap.computeIfAbsent
                                (propertyEntry.entity(), hiStateChangeEventMessage -> new HIStateChangeEventMessageImpl())
                                .setValue((String) propertyEntry.value());

                    }
                    if (propertyEntry.key().equalsIgnoreCase("status")) {
                        indicatorStateChangeEventMessageHashMap.computeIfAbsent
                                (propertyEntry.entity(), hiStateChangeEventMessage -> new HIStateChangeEventMessageImpl())
                                .setStatus((String) propertyEntry.value());

                    }
                    indicatorStateChangeEventMessageHashMap.computeIfAbsent
                            (propertyEntry.entity(), hiStateChangeEventMessage -> new HIStateChangeEventMessageImpl())
                            .setChgTimeStamp(String.valueOf(transactionData.getCommitTime()));

                    indicatorStateChangeEventMessageHashMap.computeIfAbsent
                            (propertyEntry.entity(), hiStateChangeEventMessage -> new HIStateChangeEventMessageImpl())
                            .setKsmObjId(ksmObjId);
                }
            });
        }

        if (!indicatorStateChangeEventMessageHashMap.isEmpty()){
            if (indicatorStateChangeEventMessageHashMap.size()>1){
                logger.error("HIStateChangeTransactionEventHandlerBean В одной транзакции измеменяемых KSMHI большк одного!!!! ");
            }
            try{
                indicatorStateChangeEventMessageHashMap.forEach((node,hiStateChangeEventMessage)->{
                    context.createProducer().send(indicatorStateChangeBus , hiStateChangeEventMessage);
                     /* TODO: определиьт надо ли запускать механизм пересчета если сообщение о смене статуса не ушло в шину*/
                    if (ksmCoreApplicationConfig.RECALCULATE_AFTER_EACH_HI_STATE_CHANGE) {
                        TraversalDescription affectedNodesTraversal = node.getGraphDatabase().traversalDescription()
                                .order(BranchOrderingPolicies.PREORDER_BREADTH_FIRST)
                                .relationships( ci2ciRelationshipType, Direction.OUTGOING )
                                .uniqueness( Uniqueness.RELATIONSHIP_GLOBAL )
                                ;

                        Traverser affectedNodes = affectedNodesTraversal.traverse(getRelatedCI(node));
                        HashMap<String , String> affectedNodesMap = new HashMap<>();
                        try( Transaction tx =node.getGraphDatabase().beginTx() ){
                            for(Node nd : affectedNodes.nodes())
                            {
                                String ksmObjId = nd.hasProperty("ksmObjId")?nd.getProperty("ksmObjId").toString():"none";
                                affectedNodesMap.putIfAbsent(String.valueOf(nd.getId()) , ksmObjId);
                            }
                            tx.success();
                        }


                        /* предполагается что node содержит обьект типа KSMHI и это не требует дальнейшей проверки
                        *  так же полагается что нету KSMHI которые никак не связаны с KSMCI или связаны со многими KSMCI одновременно
                        * */
                        TraversalDescription serviceTraversal = node.getGraphDatabase().traversalDescription()
                                .order(BranchOrderingPolicies.PREORDER_BREADTH_FIRST)
                                .relationships( ci2ciRelationshipType, Direction.OUTGOING )
                                .uniqueness( Uniqueness.RELATIONSHIP_GLOBAL )
                                .expand(calculateKPIStatePathExpader.setAffectedNodes(affectedNodesMap))
                                ;

                        Traverser traverser = serviceTraversal.traverse( getRelatedCI(node));
                        //ResourceIterable<Node> nodes = traverser.nodes();
                        try( Transaction tx =node.getGraphDatabase().beginTx() ){
                            for(Node nd : traverser.nodes())
                            {
                            }
                            tx.success();
                        }
                        calculateKPIStatePathExpader.reset();

                    }
                });

            }catch (Exception e){
                logger.error("Сообщения об изменении состояния индикаторов не были записаны в шину indicatorStateChangeBus из-за ошибки {}"
                         , e.getMessage());
            }

        }






        indicatorStateChangeEventMessageHashMap.clear();

    }

    @Override
    public void afterRollback(TransactionData transactionData, InternalKSMHI t) {


    }

    private Node getRelatedCI(Node nd){
        try(Transaction trn = nd.getGraphDatabase().beginTx()){
            if (
                    (nd.hasProperty("indicatorType"))
                            && (nd.getProperty("indicatorType").toString().equalsIgnoreCase(KSMIndicatorType.HI.name()))
                            && (nd.hasRelationship(() -> "AttachedHI"))
                    )
            {
                Node ciNode = nd.getSingleRelationship((() -> "AttachedHI"), Direction.OUTGOING).getEndNode();
                trn.success();
                return ciNode;


            }else{
                logger.error("у узла GDBS  либо нет свойства indicatorType или у этого поля значение не совподает " +
                        "с KSMIndicatorType.HI или у узла нет связи AttachedHI");
                trn.success();
                throw new IllegalArgumentException("node not KSMHI or have no Relation type AttachedHI");
            }

        }

    }


}
