package ru.iteco.ip.ksm.ksmobjects;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.api.KSMObjectApi;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIOGMServiceBean;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCIService;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by administrator on 29.06.2017.
 */
@Stateless(name = "KSMObjectApiEJB")
public class KSMObjectApiBean implements KSMObjectApi {
    @Inject
    private KSMCIOGMServiceBean ciService;
    @Inject @DefaultKSMLogger
    private Logger logger;
    public KSMObjectApiBean() {
        KSMCI ci = ciService.createOrUpdate(new KSMCI("ciuuid3", "CI3"));
        logger.debug("debug");
    }

    @PostConstruct
    private void initKSMObjectApiBean(){
        logger.debug("KSMObjectApiBean inited");
    }
}
