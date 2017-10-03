package ru.iteco.ip.ksm.core;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.core.internal.InternalKSMHIUpdater;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;
import ru.iteco.ip.ksm.shared.ksmevent.KSMEvent;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Scorpio on 27.09.2017.
 */

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destination"
                        , propertyValue = "KSMEventCollectorQueueEndPoint")
                , @ActivationConfigProperty(
                propertyName = "destinationType"
                , propertyValue = "javax.jms.Queue")
        }
        , name = "KSMEventCollectorEndPointEJB"
        ,mappedName = "KSMEventCollectorQueueEndPoint")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class KSMEventCollectorEndPointBean implements MessageListener {
    @Inject @DefaultKSMLogger
    private Logger logger;

    @Inject
    private InternalKSMHIUpdater internalKSMHIUpdater;
    public KSMEventCollectorEndPointBean() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void onMessage(Message message) {
        Object o = null;
        try {
            o = message.getBody(Object.class);
            if(o instanceof KSMEvent){
                logger.debug("Got KSM Event from MQ");
                KSMEvent ksmEvent = ((KSMEvent) o);
                if(ksmEvent.isResolved()){
                    internalKSMHIUpdater.updateHI(ksmEvent);
                }else{
                    System.out.println("KSMEvent with ksmEventId " + ksmEvent.getKsmEventId() + " not resolved to particular KSM HI");
                    logger.error("KSMEvent с ksmEventId {} не сопоставлен ни однму KSMHI " , ksmEvent.getKsmEventId());

                }
            }
        } catch (JMSException e) {
            logger.info("онмбка в обработке JMS {}" ,e.getMessage());
            e.printStackTrace();
        }


    }

}
