package ru.iteco.ip.ksm.shared.configmanagers;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.GDBConfig;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.Neo4JGDBConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Scorpio on 11.06.2017.
 * загружает конфигурацию KSM из файлов конфигурации или использует значения по умолчанию
 *
 */
@javax.ejb.Stateless(name = "KSMConfigurationManagerEJB")
@LocalBean
public class KSMConfigurationManagerBean implements KSMConfigurationManager {
    private final static String KSM_CFG_FILE_PATH = "neo4jDBpath";
    @Inject @DefaultKSMLogger
    private Logger logger;

    private GDBConfig gdbConfig;
    public KSMConfigurationManagerBean() {
    }

    @PostConstruct
    private void initKSMConfigurationManagerBean(){
        /* Load property cfg file or set up default values*/

        final Properties properties = new Properties();
        File file = new File("../cfg/ksm.cfg");
        boolean isF = file.isFile();
        boolean isexists = file.exists();

        String abPath = file.getAbsolutePath();
        String canonPath = "";
        try {
            canonPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = file.getPath();
        try (final InputStream stream = this.getClass().getResourceAsStream(KSM_CFG_FILE_PATH)){
            if(stream!=null){
                properties.load(stream);
            }else{
                logger.error("getResourceAsStream({}) returns null" , KSM_CFG_FILE_PATH);
                throw  new IOException("getResourceAsStream returns Null");
            }

        } catch (IOException e) {
            System.out.println("Eror reading config file from " + KSM_CFG_FILE_PATH + " reason is " + e.getMessage() );
            logger.error("Error reading Config File {} fall back to default values. error is {}" ,KSM_CFG_FILE_PATH , e.getStackTrace().toString() );
            properties.setProperty("gdbType" , "Neo4j");
            properties.setProperty("neo4jDBpath" , "C:/Temp/Neo4j/default.graphdb");
            properties.setProperty("boltProtocolPortNum" , "7687");
        }
        setGDBConfigFromProp(properties);
    }

    @Override
    public GDBConfig getGDBConfiguration() {
        return this.gdbConfig;
    }

    private void setGDBConfigFromProp(Properties properties){
        if( properties.getProperty("gdbType").equalsIgnoreCase("Neo4j")) this.gdbConfig = new Neo4JGDBConfiguration(
                properties.getProperty("neo4jDBpath")
                , Integer.parseInt(properties.getProperty("boltProtocolPortNum"))
        );

    }
}
