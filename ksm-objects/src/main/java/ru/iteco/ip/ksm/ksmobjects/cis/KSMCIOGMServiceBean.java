package ru.iteco.ip.ksm.ksmobjects.cis;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.abstracts.*;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Created by administrator on 29.06.2017.
 */
@Stateless(name = "KSMCIOGMServiceEJB")
@Default
@LocalBean
public class KSMCIOGMServiceBean
        extends BaseOGMServiceImpl<KSMCI> {
    @Inject @DefaultKSMLogger
    private Logger logger;
    public KSMCIOGMServiceBean() {
        super();
    }
    @PostConstruct
    private void initKSMCIOGMServiceBean(){
        logger.debug("KSMCIOGMServiceBean inited");
    }

    @Override
    public Class<KSMCI> getEntityType() {
        return KSMCI.class;
    }

}
