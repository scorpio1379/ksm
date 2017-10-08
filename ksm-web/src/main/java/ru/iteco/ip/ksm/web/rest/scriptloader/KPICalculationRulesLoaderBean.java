package ru.iteco.ip.ksm.web.rest.scriptloader;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;

/**
 * Created by Scorpio on 03.10.2017.
 */
@Singleton
@Startup
public class KPICalculationRulesLoaderBean {
    private static final String CALC_RULES_SCRIPT_DIR =  System.getProperty("jboss.server.data.dir") + File.separator + "ksm" + File.separator+"calcrulesscripts";

    public KPICalculationRulesLoaderBean() {
    }
    @PostConstruct
    private void initKPICalculationRulesLoaderBean(){
        System.out.println("--------------------------------------------");
        File path = new File (CALC_RULES_SCRIPT_DIR);
        if (!path.exists()) path.mkdir();
        System.out.println(new File("./calcrulesscripts").getAbsolutePath());
        System.out.println("--------------------------------------------");

    }
}
