package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.CIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.CI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.EditableCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.CISrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
    private String name = "name";
    private String description = "";
    private KSMCIType ksmCiType = KSMCIType.REGULAR;
    private String statusKPIksmObjId = "";



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
        if(!(this.ksmObjId==null)){
            CI tmpci;
            try {
                //tmpci = ciSrvc.findByKsmObjId(ksmObjId);
                tmpci = ciSrvc.find(ksmObjId);

                if(tmpci.getKsmObjType().equals(KSMObjectType.CI)){
                    ((EditableCI)tmpci).setName(this.name);
                    ((EditableCI)tmpci).setDescription(this.description);
                    ((EditableCI)tmpci).setKsmCiType(this.ksmCiType);
                    ((EditableCI)tmpci).setStatusKPIksmObjId(this.statusKPIksmObjId);
                    return ciSrvc.createOrUpdate(tmpci);

                }else{
                    logger.error("FATAL ERROR : KSMObj with id {} requested in  param constructor CIBuilderImpl may NOT be CI or it extention " , ksmObjId);
                    throw new ClassCastException("FATAL ERROR : KSMObj with id "+ksmObjId+" requested in  param constructor CIBuilderImpl may NOT be CI or it extention ");
                }
            } catch (Exception fetchErr) {

                logger.error("ERROR: unable to get any object with ksmObjId {} proceed with creation " , ksmObjId);
                /*TODO: надо как-то придумать разные инициализации билдеров с ksmObjID и без оного. или же вообще отказаться от явного присвоения ksmObjID и обьеденять обьекты по какому то иному признаку*/
                return ciSrvc.createOrUpdate(new CI_Obj.Builder(this.name).description(this.description).ksmCiType(this.ksmCiType).statusKPIksmObjId(this.statusKPIksmObjId).ksmObjId(this.ksmObjId).build());
            }

        }else{
            return ciSrvc.createOrUpdate(new CI_Obj.Builder(this.name).description(this.description).ksmCiType(this.ksmCiType).statusKPIksmObjId(this.statusKPIksmObjId).build());
        }

    }
}
