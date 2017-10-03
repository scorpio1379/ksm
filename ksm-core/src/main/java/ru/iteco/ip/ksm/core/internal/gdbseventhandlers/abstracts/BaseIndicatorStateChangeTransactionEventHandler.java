package ru.iteco.ip.ksm.core.internal.gdbseventhandlers.abstracts;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMIndicator;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;

/**
 * Created by Scorpio on 02.10.2017.
 */
public abstract class BaseIndicatorStateChangeTransactionEventHandler<INDICATORTYPE extends InternalKSMIndicator<INDICATORTYPE>> implements TransactionEventHandler<INDICATORTYPE>{

    @Inject
    @DefaultKSMLogger
    protected Logger logger;

    protected String getKsmObjIdFromGDBSNode(Node nd){
        final String ksmObjIdFieldName = "ksmObjId";
        try(Transaction trn = nd.getGraphDatabase().beginTx()){
            if (nd.hasProperty(ksmObjIdFieldName)){
                String ksmObjId = (String) nd.getProperty(ksmObjIdFieldName);
                trn.success();
                return ksmObjId;
            }else {
                logger.error("Узел GDBS с внутреннем id {} не имеет поля {} и не являяется обьектом КСМ", nd.getId() , ksmObjIdFieldName);
                trn.success();
                throw new IllegalArgumentException("Узел GDBS с внутреннем id " +nd.getId()+
                        " не имеет поля "+ksmObjIdFieldName+" и не являяется обьектом КСМ");
            }
        }
    }

    protected String getIndicatorTypeFromGDBSNode(Node nd){
        /*TODO: повторение кода из InternalKSMHIUpdaterBean нужно выделить в отдельный класс*/
        final String indicatorTypeFieldName = "indicatorType";
        try (Transaction t1 = nd.getGraphDatabase().beginTx()) {
            if (nd.hasProperty("indicatorType")) {
                String indicatorType = (String) nd.getProperty("indicatorType");
                t1.success();
                return indicatorType;
            } else {
                logger.error("Узел GDBS с внутреннем id {} не имеет поля {} и являясь обьектом КСМ не является КСМ индикатором "
                        , nd.getId(), indicatorTypeFieldName);
                t1.success();
                throw new IllegalArgumentException("Узел GDBS с внутреннем id " +nd.getId()+" не имеет поля "+indicatorTypeFieldName+
                        " и являясь обьектом КСМ не является КСМ индикатором");

            }
        }
    }
}
