package ru.iteco.ip.ksm.web.rest.scriptloader;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Scorpio on 03.10.2017.
 */
@Singleton
@Startup
public class KPICalculationRulesLoaderBean {

    public KPICalculationRulesLoaderBean() {
    }
    @PostConstruct
    private void initKPICalculationRulesLoaderBean(){
        System.out.println("--------------------------------------------");
        InputStream a = Thread.currentThread().getContextClassLoader().getResourceAsStream("KPICalculatationRule.groovy");
        InputStream b = Thread.currentThread().getContextClassLoader().getResourceAsStream("calcrulesscripts/KPICalculatationRule.groovy");
        InputStream c = Thread.currentThread().getContextClassLoader().getResourceAsStream("/calcrulesscripts/KPICalculatationRule.groovy");
        System.out.println(new File("./calcrulesscripts").getAbsolutePath());
        System.out.println("--------------------------------------------");

    }
}
