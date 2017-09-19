package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.EditableKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.KPI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.KPISrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Scorpio on 07.09.2017.
 */
@Stateless
public class KPIBuilderImpl implements KPIBuilder , InitableKPIBuilder {
    @Inject
    @DefaultKSMLogger
    private Logger logger;
    @Inject
    private KPISrvc kpiSrvc;

    /* отнаследовано из ksmObject*/
    private Long id;
    private String ksmObjId;
    private KSMObjectType ksmObjType;
    private String name;

    /* отнаследованно из ksmIndicator*/
    private String value;
    private String status;
    private KSMIndicatorType ksmIndicatorType;
    private String description;

    /* отнаследовано от ksmKPI*/
    private String kpiType;
    private String calcRuleId;
    private CI relatedCI;



    public KPIBuilderImpl() {
        this.relatedCI = null;
    }

    public KPIBuilderImpl(CI ci) {
        this.relatedCI= ci;
    }


    @Override
    public KPIBuilder ksmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
        return this;
    }

    @Override
    public KPIBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public KPI build() {
        if(!(this.ksmObjId==null)){
            KPI tmpKPI;
            try {
                 tmpKPI = kpiSrvc.find(this.ksmObjId);
                /*TODO скорее всего надо заключить в try для предотвращения nullpointerException если парамеитра типИндикатора вообще не будет*/
                if (tmpKPI.getIndicatorType().equals(KSMIndicatorType.KPI)) {
                    if (this.name!=null)((EditableKPI) tmpKPI).setName(this.name);
                    if (this.description!=null)((EditableKPI) tmpKPI).setDescription(this.description);
                    if (this.value!=null)((EditableKPI) tmpKPI).setValue(this.value);
                    if (this.status!=null)((EditableKPI) tmpKPI).setStatus(this.status);
                    if (this.ksmIndicatorType!=null)((EditableKPI) tmpKPI).setIndicatorType(this.ksmIndicatorType);
                    if (this.kpiType!=null)((EditableKPI) tmpKPI).setKpiType(this.kpiType);
                    if (this.calcRuleId!=null)((EditableKPI) tmpKPI).setKpiCalcRuleId(this.calcRuleId);
                    if (this.relatedCI!=null)((EditableKPI) tmpKPI).setRelatedCI(this.relatedCI);
                    return kpiSrvc.createOrUpdate(tmpKPI);
                }else{
                    logger.error("FATAL ERROR : KSMObj with id {} requested in  param constructor KPIBuilderImpl may NOT be KPI or it extention " , ksmObjId);
                    throw new ClassCastException("FATAL ERROR : KSMObj with id "+ksmObjId+" requested in  param constructor KPIBuilderImpl may NOT be KPI or it extention ");
                }

            }catch (Exception fetchErr){
                logger.error("ERROR: unable to get any object with ksmObjId {} proceed with creation " , ksmObjId);
                /*TODO: надо как-то придумать разные инициализации билдеров с ksmObjID и без оного. или же вообще отказаться от явного присвоения ksmObjID и обьеденять обьекты по какому то иному признаку*/
                return createNewKPI();
            }

        }else{
            logger.error("ERROR: unable to get any object with ksmObjId {} proceed with creation " , ksmObjId);
                /*TODO: надо как-то придумать разные инициализации билдеров с ksmObjID и без оного. или же вообще отказаться от явного присвоения ksmObjID и обьеденять обьекты по какому то иному признаку*/
                return createNewKPI();
        }


    }

    @Override
    public KPI get(String ksmObjId) {
        return kpiSrvc.find(ksmObjId);
    }

    private KPI createNewKPI(){
        KPI_Obj tmpKPI = new KPI_Obj();
        if (this.ksmObjId!=null) tmpKPI.setKsmObjId(this.ksmObjId);
        if (this.name!=null)tmpKPI.setName(this.name);
        if (this.description!=null)tmpKPI.setDescription(this.description);
        if (this.value!=null)tmpKPI.setValue(this.value);
        if (this.status!=null)tmpKPI.setStatus(this.status);
        if (this.kpiType!=null)tmpKPI.setKpiType(this.kpiType);
        if (this.calcRuleId!=null)tmpKPI.setKpiCalcRuleId(this.calcRuleId);
        if (this.relatedCI!=null)tmpKPI.setRelatedCI(this.relatedCI);
        return kpiSrvc.createOrUpdate(tmpKPI);
    }

    @Override
    public KPIBuilder description(String description) {
        this.description=description;
        return this;
    }

    @Override
    public KPIBuilder value(String value) {
        this.value=value;
        return this;
    }

    @Override
    public KPIBuilder status(String status) {
        this.status=status;
        return this;
    }

    @Override
    public KPIBuilder ksmIndicatorType(KSMIndicatorType ksmIndicatorType) {
        this.ksmIndicatorType = ksmIndicatorType;
        return this;
    }

    @Override
    public KPIBuilder kpiType(String kpiType) {
        this.kpiType = kpiType;
        return this;
    }

    @Override
    public KPIBuilder calcRuleId(String calcRuleId) {
        this.calcRuleId = calcRuleId;
        return this;
    }

    @Override
    public void setCI(CI ci) {
        this.relatedCI= ci;
    }
}
