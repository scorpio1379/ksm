package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.neo4j.ogm.transaction.Transaction;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.CIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.CI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.CISrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Scorpio on 06.09.2017.
 */
@Stateless
public  class CIBuilderImpl implements CIBuilder {
    @Inject @DefaultKSMLogger
    private Logger logger;
    @Inject
    private CISrvc ciSrvc;

    private String ksmObjId = null;
    private String name = null;
    private String description = null;
    private KSMCIType ksmCiType = null;
    private String statusKPIksmObjId = null;



    public CIBuilderImpl() {
    }

    @Override
    public CIBuilder ksmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
        return this;
    }


    @Override
    public CIBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CIBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CIBuilder ksmCiType(KSMCIType ksmCiType) {
        this.ksmCiType = ksmCiType;
        return this;
    }

    @Override
    public CIBuilder statusKPIksmObjId(String statusKPIksmObjId) {
        this.statusKPIksmObjId = statusKPIksmObjId;
        return this;
    }



    @Override
    public CI build() {
        /*TODO:ошибочная стратегия, нужно убрать метод в абстрактный базовый класс,который как-то болжен быть параметрезирован конкретной реализацией интерфейса ОБЬкекта (CI KPI или что-то еще)
         */
        try(Transaction trn = this.ciSrvc.beginTransaction(Transaction.Type.READ_WRITE)) {
            CI tmpCi = null;
            if (this.ksmObjId != null) tmpCi = ciSrvc.find(ksmObjId);
            /* TODO: проработать создание обьекта через метод соответствующем сервисе*/
            if (tmpCi == null) tmpCi = new CI_Obj(); //this.getNewCI_Obj();
            //this.ksmObjId==null ? (tmpci1 = this.getNewCI_Obj()) : (tmpci1 = ciSrvc.find(ksmObjId));


            /* if (this.ksmObjId != null) ((CI_Obj) tmpCi).setKsmObjId(this.ksmObjId);*/
            /*TODO: КОСТЫЛЬ!!!!! нужно придумать как сделать caseInsensitive ksmObjID, если поле в GDB чувствительно к регистру*/
            if (this.ksmObjId != null) ((CI_Obj) tmpCi).setKsmObjId(UUID.fromString(this.ksmObjId).toString());
            if (this.name != null) tmpCi.setName(this.name);
            if (this.description != null) tmpCi.setDescription(this.description);
            if (this.ksmCiType != null) tmpCi.setKsmCiType(this.ksmCiType);
             /*TODO: КОСТЫЛЬ!!!!! нужно придумать как сделать caseInsensitive statusKPIksmObjId, если поле в GDB чувствительно к регистру*/
            if (this.statusKPIksmObjId != null) tmpCi.setStatusKPIksmObjId(UUID.fromString(this.statusKPIksmObjId).toString());

            CI returnrdCi = ciSrvc.createOrUpdate(tmpCi);
            trn.commit();
            return returnrdCi;

        }


    }

    @Override
    public CI get(String ksmObjId) {
        return ciSrvc.find(ksmObjId);
    }

    private CI getNewCI_Obj(){
        CI_Obj tmpCi = new CI_Obj();
        if (this.ksmObjId!=null) tmpCi.setKsmObjId(this.ksmObjId);
        if (this.name!=null)tmpCi.setName(this.name);
        if (this.description!=null)tmpCi.setDescription(this.description);
        if (this.ksmCiType!=null)tmpCi.setKsmCiType(this.ksmCiType);
        if (this.statusKPIksmObjId!=null) tmpCi.setStatusKPIksmObjId(this.statusKPIksmObjId);
        return tmpCi;

    }
}
