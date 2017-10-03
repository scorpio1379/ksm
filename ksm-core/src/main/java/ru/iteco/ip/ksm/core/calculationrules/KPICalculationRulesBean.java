package ru.iteco.ip.ksm.core.calculationrules;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.HashMap;

/**
 * Created by Scorpio on 03.10.2017.
 */
@Singleton(name = "KPICalculationRulesEJB")
@LocalBean
public class KPICalculationRulesBean {
    @Inject @DefaultKSMLogger
    private Logger logger;
    private HashMap<String , Class<?>> kpiCalculationRules = new HashMap<>();
    public KPICalculationRulesBean() {
    }
}
