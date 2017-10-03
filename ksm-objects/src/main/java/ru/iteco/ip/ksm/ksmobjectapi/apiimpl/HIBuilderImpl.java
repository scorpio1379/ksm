package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.HIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.EditableHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.HI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.HISrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Scorpio on 09.09.2017.
 */
@Stateless
public class HIBuilderImpl implements HIBuilder, InitableHIBuilder {
    @Inject
    @DefaultKSMLogger
    private Logger logger;
    @Inject
    private HISrvc hiSrvc;

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
    private String hiType;
    private CI relatedCI;

    @Override
    public HIBuilder ksmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
        return this;
    }

    @Override
    public HIBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public HI build() {
        if(!(this.ksmObjId==null)){
            HI tmpHI;
            try {
                tmpHI = hiSrvc.find(this.ksmObjId);
                /*TODO скорее всего надо заключить в try для предотвращения nullpointerException если парамеитра типИндикатора вообще не будет*/
                if (tmpHI.getIndicatorType().equals(KSMIndicatorType.KPI)) {

                    if (this.name!=null)tmpHI.setName(this.name);
                    if (this.description!=null)tmpHI.setDescription(this.description);
                    if (this.value!=null)((EditableHI) tmpHI).setValue(this.value);
                    if (this.status!=null)((EditableHI) tmpHI).setStatus(this.status);
                    if (this.hiType!=null)tmpHI.setHiType(this.hiType);
                    if (this.relatedCI!=null)((EditableHI) tmpHI).setRelatedCI(this.relatedCI);
                    return hiSrvc.createOrUpdate(tmpHI);
                }else{
                    logger.error("FATAL ERROR : KSMObj with id {} requested in  param constructor KPIBuilderImpl may NOT be KPI or it extention " , ksmObjId);
                    throw new ClassCastException("FATAL ERROR : KSMObj with id "+ksmObjId+" requested in  param constructor KPIBuilderImpl may NOT be KPI or it extention ");
                }

            }catch (Exception fetchErr){
                logger.error("ERROR: unable to get any object with ksmObjId {} proceed with creation " , ksmObjId);
                /*TODO: надо как-то придумать разные инициализации билдеров с ksmObjID и без оного. или же вообще отказаться от явного присвоения ksmObjID и обьеденять обьекты по какому то иному признаку*/
                return createNewHI();
            }

        }else{
            logger.error("ERROR: unable to get any object with ksmObjId {} proceed with creation " , ksmObjId);
                /*TODO: надо как-то придумать разные инициализации билдеров с ksmObjID и без оного. или же вообще отказаться от явного присвоения ksmObjID и обьеденять обьекты по какому то иному признаку*/
            return createNewHI();
        }
    }

    @Override
    public HI get(String ksmObjId) {
        return hiSrvc.find(ksmObjId);
    }

    @Override
    public void setCI(CI ci) {
        this.relatedCI= ci;
    }

    @Override
    public HIBuilder description(String description) {
        this.description=description;
        return this;
    }

    @Override
    public HIBuilder value(String value) {
        this.value=value;
        return this;
    }

    @Override
    public HIBuilder status(String status) {
        this.status=status;
        return this;
    }

    @Override
    public HIBuilder ksmIndicatorType(KSMIndicatorType ksmIndicatorType) {
        this.ksmIndicatorType = ksmIndicatorType;
        return this;
    }

    @Override
    public HIBuilder hiType(String hiType) {
        this.hiType=hiType;
        return this;
    }

    private HI createNewHI(){
        /* TODO: проработать создание обьекта через метод соответствующем сервисе*/
        HI_Obj tmpHI = new HI_Obj();
        //if (this.ksmObjId!=null) tmpHI.setKsmObjId(this.ksmObjId);
        /*TODO: КОСТЫЛЬ!!!!! нужно придумать как сделать caseInsensitive ksmObjID, если поле в GDB чувствительно к регистру*/
        if (this.ksmObjId!=null) tmpHI.setKsmObjId(UUID.fromString(this.ksmObjId).toString());
        if (this.name!=null)tmpHI.setName(this.name);
        if (this.description!=null)tmpHI.setDescription(this.description);
        if (this.value!=null)tmpHI.setValue(this.value);
        if (this.status!=null)tmpHI.setStatus(this.status);
        if (this.hiType!=null)tmpHI.setHiType(this.hiType);
        if (this.relatedCI!=null)tmpHI.setRelatedCI(this.relatedCI);
        return hiSrvc.createOrUpdate(tmpHI);
    }
}
