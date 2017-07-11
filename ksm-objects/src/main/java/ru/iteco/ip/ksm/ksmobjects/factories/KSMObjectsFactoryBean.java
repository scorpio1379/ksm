package ru.iteco.ip.ksm.ksmobjects.factories;

import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Created by administrator on 04.07.2017.
 */
@Stateless(name = "KSMObjectsFactoryEJB")
@LocalBean
@Default
public class KSMObjectsFactoryBean implements KSMObjectFactory {

    @Inject
    private KSMCIService ksmciService;

    public KSMObjectsFactoryBean() {
    }

    @Override
    public KSMCI createService(String uuid, String name) {
        KSMCI srvcCi = ksmciService.createServiceCI(uuid, name);
        return srvcCi;
    }
    @Override
    public KSMCI createKSMCI(String uuid , String name , KSMCIType ksmciType){
       return  ksmciService.craeteRegularCI(uuid, name, ksmciType);
    }
}
