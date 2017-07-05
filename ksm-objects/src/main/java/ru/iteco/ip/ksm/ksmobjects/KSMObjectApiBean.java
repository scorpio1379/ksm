package ru.iteco.ip.ksm.ksmobjects;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.api.IKSMObjectApiLocal;
import ru.iteco.ip.ksm.ksmobjects.api.IKSMObjectApiRemote;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;
import ru.iteco.ip.ksm.ksmobjects.factories.KSMObjectFactory;
import ru.iteco.ip.ksm.ksmobjects.factories.KSMObjectsFactoryBean;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by administrator on 29.06.2017.
 */
@Stateless(name = "KSMObjectApiEJB" , mappedName = "KSMObjectApi")
@Remote(IKSMObjectApiRemote.class)
@Local(IKSMObjectApiLocal.class)
public class KSMObjectApiBean implements IKSMObjectApiRemote , IKSMObjectApiLocal {
    @Inject @DefaultKSMLogger
    private Logger logger;

    @Inject
    private KSMObjectFactory ksmObjFab;
    public KSMObjectApiBean() {

    }

    @PostConstruct
    private void initKSMObjectApiBean(){
        logger.debug("debug");
        logger.debug("KSMObjectApiBean inited");
    }

    @Override
    public void test() {

    }

    @Override
    public KSMCI createService(String uuid, String name) {
        return ksmObjFab.createService(uuid , name);
    }
}
