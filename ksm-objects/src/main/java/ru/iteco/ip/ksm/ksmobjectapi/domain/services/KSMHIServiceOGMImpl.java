package ru.iteco.ip.ksm.ksmobjectapi.domain.services;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMObject;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHIType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.indicatortocirelationships.AttachedHIKSMRelationShip;
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
public class KSMHIServiceOGMImpl
        extends BaseKSMObjectServiceOGMImpl<KSMHI>
        implements KSMHIService {
    @Override
    public Class<KSMHI> getEntityType() {
        return KSMHI.class;
    }


    @Override
    public KSMHI createKSMHIAtKSMCI(KSMCI<?> ci, String ksmHiObjId, String name, KSMHIType ksmhiType) {
                /*TODO: проверить есть ли у HI связь с другим CI и если есть ее надо разорвать*/
        KSMHI hi = new KSMHI();
        BaseKSMObject ksmObj = this.createOrUpdate(hi);
        AttachedHIKSMRelationShip attachedHIRelationShip = new AttachedHIKSMRelationShip();
        attachedHIRelationShip.setStartNode(hi);
        attachedHIRelationShip.setEndNode(ci);
        try {
            session.save(attachedHIRelationShip , 1);
        } catch (Exception e) {
            logger.error("произошла ошибка в процессе попытки создания связи HI c ksmObjId {} c CI c ksmObjId {} , возможно такая связь УЖЕ существует, ОШИБКА {}" , hi.getKsmObjId() ,ci.getKsmObjId() ,e.getStackTrace());
        }
        ksmObj = this.findByKsmObjId(ksmObj.getKsmObjId());
        /* TODO: надо проверять тип вернувшегося обьекта?*/
        return (KSMHI) ksmObj;
    }
}
