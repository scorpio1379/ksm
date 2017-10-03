package ru.iteco.ip.ksm.core.internal.ksmobjectupdaters;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import ru.iteco.ip.ksm.core.internal.InternalKSMHIUpdater;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.shared.ksmevent.KSMEvent;

import javax.ejb.*;

/**
 * Created by Scorpio on 27.09.2017.
 */
@Stateless(name = "InternalKSMHIUpdaterEJB")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InternalKSMHIUpdaterBean
        extends InternalKSMIndicatorUpdaterBean<InternalKSMHI>
        implements InternalKSMHIUpdater{

    public InternalKSMHIUpdaterBean() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void updateHI(KSMEvent ksmEvent) {
        Node nd = this.findNodeByKsmObjId(ksmEvent.getTargetKSMObjId());
        boolean isHIType = false;
        if ((nd!=null) ) {
            /*TODO:провериьт верна ли стратегия проверок!!!!!*/

            try (Transaction t1 = nd.getGraphDatabase().beginTx()) {
                if (nd.hasProperty("indicatorType")) {
                    String a = KSMIndicatorType.HI.name();
                    String b = (String) nd.getProperty("indicatorType");
                    if (((String)nd.getProperty("indicatorType")).equalsIgnoreCase(KSMIndicatorType.HI.name())) {
                        isHIType = true;
                    } else {
                        logger.error("KSMEvent с KSMEventId {} сопоставлен с KSMHI с ksmObjId {} найденный узлел" +
                                        " с таким ksmObjId в поле  indicatorType не содержит ожидаемого значения KSMIndicatorType.HI" +
                                        " и скорее всего не является KSMHI"
                                , ksmEvent.getKsmEventId(), ksmEvent.getTargetKSMObjId());
                    }
                } else {
                    logger.error("KSMEvent с KSMEventId {} сопоставлен с KSMHI с ksmObjId {} найденный узлел" +
                                    " с таким ksmObjId в поле  indicatorType не содержит ожидаемого поля indicatorType и " +
                                    "скорее всего не является KSMHI"
                            , ksmEvent.getKsmEventId(), ksmEvent.getTargetKSMObjId());

                }
            }
        }
        if (isHIType){
            try (Transaction trnx = nd.getGraphDatabase().beginTx()){
                this.updateInternalKSMHIStatus(ksmEvent.getStatus(),nd);
                this.updateInternalKSMHIValue(ksmEvent.getValue(),nd);
                this.updateInternalKSMHIChgTimeStamp(ksmEvent.getValue(),nd);
                this.updateInternalKSMHIDebugInfo(ksmEvent.getValue(),nd);
                trnx.success();
            }
        } else {
            /*TODO придумать что делать с таким KSMEvent`ом*/
            logger.error("KSMEvent с KSMEventId {} сопоставлен с KSMHI с ksmObjId {} однако в GraphDataBase узла" +
                    " с таким ksmObjId нет или их больше одного "
                    ,ksmEvent.getKsmEventId(),ksmEvent.getTargetKSMObjId() );
        }

    }

    private void updateInternalKSMHIDebugInfo(String debugInfo , Node nd) {
        this.updateInternalKSMIndicatorDebugInfo(debugInfo, nd);
    }

    private void updateInternalKSMHIChgTimeStamp(String chgTimeStamp, Node nd) {
        this.updateInternalKSMIndicatorChgTimeStamp(chgTimeStamp, nd);
    }

    private void updateInternalKSMHIValue(String value, Node nd) {
        this.updateInternalKSMIndicatorValue(value , nd);
    }

    private void updateInternalKSMHIStatus(String status, Node nd) {
        this.updateInternalKSMIndicatorStatus(status, nd);
    }



}
