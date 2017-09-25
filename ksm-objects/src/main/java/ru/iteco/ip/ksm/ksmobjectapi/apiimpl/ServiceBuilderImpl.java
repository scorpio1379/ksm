package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.neo4j.ogm.transaction.Transaction;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.ServiceBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.api.ServiceModel;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.EditableAObj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.ServiceSrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scorpio on 18.09.2017.
 */
@Stateless
public class ServiceBuilderImpl implements ServiceBuilder {
    @Inject @DefaultKSMLogger
    Logger logger;
    @Inject
    private ServiceSrvc serviceSrvc;
    private String ksmObjId = null;
    private String name = null;
    private String description = null;
    private KSMCIType ksmCiType = KSMCIType.SERVICE;
    private String statusKPIksmObjId = null;


    @Override
    public ServiceBuilder ksmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
        return this;
    }


    @Override
    public ServiceBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ServiceBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ServiceBuilder ksmCiType(KSMCIType ksmCiType) {
        this.ksmCiType = ksmCiType;
        return this;
    }

    @Override
    public ServiceBuilder statusKPIksmObjId(String statusKPIksmObjId) {
        this.statusKPIksmObjId = statusKPIksmObjId;
        return this;
    }

    @Override
    public Service get(String ksmObjId) {
        return serviceSrvc.find(ksmObjId);
    }

    @Override
    public Service build()  {
        /*TODO:ошибочная стратегия, нужно убрать метод в абстрактный базовый класс,который как-то болжен быть параметрезирован конкретной реализацией интерфейса ОБЬкекта (CI KPI или что-то еще)
         */
        try(Transaction trn = this.serviceSrvc.beginTransaction(Transaction.Type.READ_WRITE)) {
            Service tmpCi = null;
            if (this.ksmObjId != null) tmpCi = serviceSrvc.find(ksmObjId);
            if (tmpCi == null) tmpCi = serviceSrvc.instansiateFromEntityType(); //this.getNewCI_Obj();
            //this.ksmObjId==null ? (tmpci1 = this.getNewCI_Obj()) : (tmpci1 = ciSrvc.find(ksmObjId));

            if (this.ksmObjId != null) ((EditableAObj) tmpCi).setKsmObjId(this.ksmObjId);
            if (this.name != null) tmpCi.setName(this.name);
            if (this.description != null) tmpCi.setDescription(this.description);
            if (this.ksmCiType != null) tmpCi.setKsmCiType(this.ksmCiType);
            if (this.statusKPIksmObjId != null) tmpCi.setStatusKPIksmObjId(this.statusKPIksmObjId);

            Service returnrdCi = serviceSrvc.createOrUpdate(tmpCi);
            trn.commit();

            return returnrdCi;

        } catch (IllegalAccessException | InstantiationException  e) {
            e.printStackTrace();
            /*TODO: убрать возврат null в Exception*/
            return null;
        }


    }

    @Override
    public Set<Service> getAll(){
        HashSet<Service> services = new HashSet<>();
        serviceSrvc.findAll().forEach(it -> services.add(it));
        return services;
    }

    @Override
    public ServiceModel getServiceModel(Service service) {
        //ServiceModel serviceModel = new ServiceModelImpl();
        ServiceModel serviceModel = serviceSrvc.getServiceModel(service);
        //serviceModel.setRelatedCis(serviceSrvc.getAllRelatedCis(service));
       // serviceModel.setCi2ciRelationShips(this.serviceSrvc.getAllRelations(service));
        return serviceModel;
    }
}
