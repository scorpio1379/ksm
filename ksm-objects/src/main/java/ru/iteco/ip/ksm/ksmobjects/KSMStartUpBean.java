package ru.iteco.ip.ksm.ksmobjects;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;

/**
 * Created by Scorpio on 04.06.2017.
 */

@Startup
@Singleton
public class KSMStartUpBean {

    private static final String DB_PATH = "C:/Temp/Neo4j/default.graphdb";
    private GraphDatabaseService gdbs = null ;

    public KSMStartUpBean() {
    }
    @PostConstruct
    public void init() {
        Logger logger = LoggerFactory.getLogger("KSMStartUpBean");
        System.out.println("Starting Up KSMStartUpBean...");
        logger.error("Starting Up KSMStartUpBean...");
        this.gdbs = new GraphDatabaseFactory().newEmbeddedDatabase(new File(DB_PATH));
        logger.error("Graph DB Srarteed UP");
/*
        Configuration cfg = new Configuration.Builder()
                //.uri("bolt://localhost:7687")
                .uri("file:/" + "C:/Temp/Neo4j/default.graphdb")
                .build();
                */
        Configuration cfg = new Configuration();
        cfg.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
                .setURI("file:/C:/Temp/Neo4j/default.graphdb");
        Components.configure(cfg);
        EmbeddedDriver driver = new EmbeddedDriver(this.gdbs);
        Components.setDriver(driver);

        cfg.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");

        SessionFactory sessionFactory = new SessionFactory(cfg, "ru.iteco.ip.ksm.ksmobjects");
        logger.error("Session factory created...");
        Session s = sessionFactory.openSession();
        KSMCI ci = new KSMCI();
        s.save(ci);
        System.out.println();





    }
    @PreDestroy
    public void disposeKSMStartUpBean(){
        try{
            if(this.gdbs!=null){
                this.gdbs.shutdown();
            }
        }finally {
            System.out.println("Destroyed");
        }
    }
}
