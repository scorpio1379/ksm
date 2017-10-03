package ru.iteco.ip.ksm.core.internal.ksmobjectupdaters;

import org.neo4j.graphdb.*;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.core.internal.InternalKSMObjectUpdater;
import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMObject;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Created by Scorpio on 27.09.2017.
 */
@Stateless(name = "InternalKSMObjectUpdaterEJB")
public class InternalKSMObjectUpdaterBean<INTERNALKSMOBJECTTYPE extends InternalKSMObject<INTERNALKSMOBJECTTYPE>>
        implements InternalKSMObjectUpdater<INTERNALKSMOBJECTTYPE> {
    @Inject @DefaultKSMLogger
    protected Logger logger;
    @Inject @Default
    protected GraphDatabaseService graphDatabaseService;
    public InternalKSMObjectUpdaterBean() {

    }

    protected Node findNodeByKsmObjId(String ksmObjId){
        /*TODO: прдумать как связать label с аннотацие к доменному классу, или как-то еще узнавать label всех KSMObject*/
        try(Transaction trn = this.graphDatabaseService.beginTx()){
            Node nd = this.graphDatabaseService.findNode(Label.label("AObject"),"ksmObjId" , ksmObjId);
            trn.success();
            return nd;
        }catch (MultipleFoundException mfe){
            return null;
        }
    }

    protected void updateInternalKSMObjectProperty(String fieldName, String fieldValue , Node   nd) {
        logger.info("предполагается что значениея свойств узлов имеют тип String");
        //try(Transaction trn = nd.getGraphDatabase().beginTx()){

            if (nd.hasProperty(fieldName)){
                Object propVal = nd.getProperty(fieldName);
                if (!((String) propVal).equalsIgnoreCase(fieldValue)){
                    nd.setProperty(fieldName,fieldValue);
                    /* TODO: подумать надо ли забирать данные из БД в логировании...*/
                    logger.info("свойство {} узла с ksmObjId {} было {}  стало  {}" , fieldName ,nd.getProperty("ksmObjId")
                            ,propVal, nd.getProperty(fieldName));
                }else{
                    logger.info("свойство {} узла с ksmObjId {} было {}  но не стало  {}" , fieldName ,nd.getProperty("ksmObjId")
                            ,nd.getProperty(fieldName), fieldValue);
                }
            }else{
                logger.info("свойсва {} нет у узла с ksmObjId {}", fieldName , nd.getProperty("ksmObjId"));
                nd.setProperty(fieldName,fieldValue);
            }
        //    trn.success();
       // }
    }
}
