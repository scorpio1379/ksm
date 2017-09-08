package ru.iteco.ip.ksm.ksmobjectapi.domain.services;

import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMIndicator;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMObject;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPIType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMObjectServiceOGMImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * Created by Scorpio on 21.06.2017.
 */
@LocalBean
@Default
@Stateless
public class KSMKPIServiceOGMImpl
        extends BaseKSMObjectServiceOGMImpl<KSMKPI>
        implements KSMKPIService {

    @Override
    public Class<KSMKPI> getEntityType() {
        return KSMKPI.class;
    }

    @Override
    public KSMKPI createKSMKPIAtKSMCI(KSMCI ci
            , String ksmKpiObjId
            , String name
            , KSMKPIType kpiType) {
                /*TODO: проверить есть ли у KPI связь с другим CI и если есть ее надо разорвать*/
        //KSMKPI oldkpi = (KSMKPI)  this.findByKsmObjId(kpiKsmObjId);
        KSMKPI kpi = new KSMKPI();

        BaseKSMObject ksmObj = this.createOrUpdate(kpi);

        /* todo: такак KPI без CI быть не может, то возможно стоиит внести код создания связи непосредственно в тело сервиса KPI?
         */

        //TstAttachedKPIRelationShip attachedKPIRel = new TstAttachedKPIRelationShip(kpi,ci);
        try {
            //session.save(attachedKPIRel , 1);
        } catch (Exception e) {
            logger.error("произошла ошибка в процессе попытки создания связи KPI c ksmObjId {} c CI c ksmObjId {} , возможно такая связь УЖЕ существует, ОШИБКА {}" , kpi.getKsmObjId() ,ci.getKsmObjId() ,e.getStackTrace());
        }
        ksmObj = this.findByKsmObjId(ksmObj.getKsmObjId());

         /* TODO: надо проверять тип вернувшегося обьекта?*/
        logger.debug(" найден обьект с ksmObjId {} и типом {}" , ksmObj.getKsmObjId() , ksmObj.getKSMObjType());
        if(ksmObj.getKSMObjType() == KSMObjectType.INDICATOR){
            BaseKSMIndicator ksmBaseIndicator= (BaseKSMIndicator) ksmObj;
            logger.debug(" найден индикатор с ksmObjId {} и типом {}" , ksmBaseIndicator.getKsmObjId() , ksmBaseIndicator.getIndicatorType());
            if(ksmBaseIndicator.getIndicatorType() == KSMIndicatorType.KPI){
                KSMKPI ksmkpi = (KSMKPI) ksmBaseIndicator;
                logger.debug(" найден KPI с ksmObjId {} и типом {}" , ksmkpi.getKsmObjId() , ksmkpi.getKpiType());
                return ksmkpi;
            }else{
                logger.error("индикатор с ksmObjId {} и типом {} не явлется подклассом класса KSMKPI" , ksmBaseIndicator.getKsmObjId() , ksmBaseIndicator.getIndicatorType());
                throw new ClassCastException("KSM Object with ksmObjId " + ksmBaseIndicator.getKsmObjId() + "not a KSMKPI subclass object");
            }
        }else {
            throw new ClassCastException("KSM Object with ksmObjId " + ksmObj.getKsmObjId() + "not a KSMBaseIndicator subclass object");
        }
    }
}
