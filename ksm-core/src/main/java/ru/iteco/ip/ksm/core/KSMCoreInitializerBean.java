package ru.iteco.ip.ksm.core;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;
import ru.iteco.ip.ksm.shared.configmanagers.GDBType;
import ru.iteco.ip.ksm.shared.configmanagers.KSMConfigurationManager;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.GDBConfig;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.Neo4JGDBConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.File;

import static javax.ejb.LockType.READ;

/**
 * Created by Scorpio on 11.06.2017.
 * класс для первичной инициализации приложения
 */
@Singleton(name = "KSMCoreInitializerEJB")
@Startup
@Lock(READ)
public class KSMCoreInitializerBean {
    @Inject @Default
    private  KSMConfigurationManager ksmCfgMgr;

    @Inject @DefaultKSMLogger
    private Logger logger;

    private GraphDatabaseService gdbs;


    public KSMCoreInitializerBean() {
    }

    @PostConstruct
    private void initKSMCoreInitializerBean(){
        GDBConfig gdbConfig = ksmCfgMgr.getGDBConfiguration();
        if(gdbConfig.getGdbType().equals(GDBType.Neo4J)){
            this.gdbs = initNeo4JDB((Neo4JGDBConfiguration) gdbConfig);
            if (this.gdbs == null){
                logger.error("GraphDatabaseService not inited");
                throw new IllegalArgumentException("GraphDatabaseService not inited");
            }else if(this.gdbs.isAvailable(100)) {
                /* TODO :надо создать event о том что база инициализировалась и готова к работе*/
                logger.info("База Готова к работе");
                System.out.println("База Готова к работе");
            } else{
                logger.error("GraphDataBaseService is NOT Null , but not available for 100 mils ");
                System.out.println("GraphDataBaseService is NOT Null , but not available for 100 mils ");
            }
        }
    }

    @PreDestroy
    public void disposeKSMCoreInitializerBean(){
        try {
            if(this.gdbs!=null){
                logger.info("Disposing Graph DataBase Service");
                this.gdbs.shutdown();
            }
        } catch (Exception e) {
            System.out.println("Error trying to Disposing Graph DataBase Service");
            logger.error("Error trying to Disposing Graph DataBase Service with error " + e.getStackTrace());
        }
    }

    private GraphDatabaseService initNeo4JDB(Neo4JGDBConfiguration gdbConfig) {
                /*TODO: must change to relevant GDB creation method */
        if(gdbConfig.getGdbType().equals(GDBType.Neo4J)){
            logger.info("gdbs type is Neo4j");
            /* создает БД если еще не создана, и добовляет возможность подключения по BOLT протоколу*/
            GraphDatabaseSettings.BoltConnector bolt = GraphDatabaseSettings.boltConnector( "0" );
            GraphDatabaseService gdbs = new GraphDatabaseFactory()
                    .newEmbeddedDatabaseBuilder( new File(gdbConfig.getNeo4jDBPath()))
                    .setConfig( bolt.type, "BOLT" )
                    .setConfig( bolt.enabled, "true" )
                    .setConfig( bolt.listen_address, "0.0.0.0:" + gdbConfig.getBoltProtocolNumber() )
                    .setConfig( bolt.advertised_address, "0.0.0.0:" + (gdbConfig.getBoltProtocolNumber()+10) )
                    .newGraphDatabase();

            if( gdbs.isAvailable(100)){
                logger.info("Neo4J DB Initiazied. Bolt Available at port {}" ,gdbConfig.getBoltProtocolNumber());
                /* если БД запущена то выключаем БД и инициализируем  OGM EmbeddedDriver с инициализацией БД через драйвер*/
                /*
                //gdbs.shutdown();
                String str = gdbConfig.getNeo4jDBPath();

                URI uri = URI.create(str);
                Configuration ogmcfg = new Configuration.Builder()
                        .uri("file:/"+gdbConfig.getNeo4jDBPath())
                        .build();
                //EmbeddedDriver embeddedDriver = new EmbeddedDriver(gdbs);
                //embeddedDriver.configure(ogmcfg);
                //Configuration cfg = embeddedDriver.getConfiguration();
                //SessionFactory sessionFactory = new SessionFactory( embeddedDriver,"ru.iteco.ip.ksm.ksmobjects");

                logger.error("Session factory created...");
                this.ogmEmbeddedDriver = embeddedDriver;


                return embeddedDriver;
                */
                return gdbs;



            }else{
                logger.error("Graph DataBaseService not inited with path {} " , gdbConfig.getNeo4jDBPath());
                /*TODO: must implement exception instead return null */
                throw new IllegalArgumentException("Graph DataBaseService not inited");
            }

        }else{
            /*TODO: must implement exception instead return null */

            logger.error("gdbs type is NOT!!! Neo4j");
            throw new IllegalArgumentException("gdbs type is NOT!!! Neo4j");

        }

    }

    @Produces
    public GraphDatabaseService getGDBS(){
        return this.gdbs;
    }


}
