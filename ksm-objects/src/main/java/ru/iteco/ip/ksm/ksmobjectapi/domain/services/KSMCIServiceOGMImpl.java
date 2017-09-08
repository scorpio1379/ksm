package ru.iteco.ip.ksm.ksmobjectapi.domain.services;


import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.IKSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMCIServiceOGMImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * Created by administrator on 04.07.2017.
 */
@LocalBean
@Default
@Stateless
public class KSMCIServiceOGMImpl
        extends BaseKSMCIServiceOGMImpl<IKSMCI>
        implements  KSMCIService


{
    @Override
    public Class getEntityType() {
        return KSMCI.class;
    }
    /*
             @Override
             public  T createOrUpdate(T ksmObject) {
                 T ci;
                 try {
                     ci = (T) this.findByKsmObjId(ksmObject.getKsmObjId());
                 } catch (ArrayIndexOutOfBoundsException noObjExc) {
                     noObjExc.printStackTrace();
                     logger.info("в граф-базе нет обьекта с ksmObjectId = {} , создаем новый обьект...", ksmObject.getKsmObjId());
                     ci = (T) new KSMCI();
                 } catch (Exception restErr){
                     logger.info("в процессе поиска КЭ по ksmObjId =  {} произошла ошибка, создание обьекта прервано", ksmObject.getKsmObjId());
                     throw restErr;
                 }

                 //T createdOrUpdatedCI = (T) this.createOrUpdate(ci);
                 //return createdOrUpdatedCI;
                 return null;
             }
             */





    //@Override
    public KSMCI getCIByKsmObjId(String ciKsmObjId) {
        KSMCI ksmObj = (KSMCI) findByKsmObjId(ciKsmObjId);
        /* TODO: надо проверять тип вернувшегося обьекта?*/
        logger.debug(" найден обьект с ksmObjId {} и типом {}" , ksmObj.getKsmObjId() , ksmObj.getKSMObjType());
        if(ksmObj.getKSMObjType() == KSMObjectType.CI){
            KSMCI ci = (KSMCI) ksmObj;
            return ci;
        }else {
            throw new ClassCastException("KSM Object with ksmObjId " + ciKsmObjId + "not a KSMBaseCI subclass object");
        }
    }

}
