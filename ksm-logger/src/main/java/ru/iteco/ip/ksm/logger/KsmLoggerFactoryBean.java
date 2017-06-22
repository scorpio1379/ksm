package ru.iteco.ip.ksm.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by Scorpio on 12.06.2017.
 */
@Stateless(name = "KsmLoggerFactoryEJB")
public class KsmLoggerFactoryBean {
    public KsmLoggerFactoryBean() {
    }
    @Produces @DefaultKSMLogger
    public Logger produceDefaultKSMLogger(InjectionPoint ip){
        return LoggerFactory.getLogger(ip.getClass().getCanonicalName());
    }
}
