package ru.iteco.ip.ksm.ksmobjects;


import org.neo4j.graphdb.GraphDatabaseService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

//import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
//import org.neo4j.ogm.session.Session;
//import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Scorpio on 04.06.2017.
 */


@Singleton
public class KSMStartUpBean {

    private static final String DB_PATH = "C:/Temp/Neo4j/default.graphdb";
    private GraphDatabaseService gdbs = null ;

    public KSMStartUpBean() {
    }
    @PostConstruct
    public void init() {


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
