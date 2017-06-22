package ru.iteco.ip.ksm.ksmobjects;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.annotations.KSMObjectsOGMSessionFactory;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Scorpio on 17.06.2017.
 * класс для получеиния экземпляров других классов из БД
 */
@Stateless(name = "Neo4JEntityManagerEJB")
//@Singleton(name = "Neo4JEntityManagerEJB")
public class Neo4JEntityManagerBean {
    /**
     * конфигурация БД
     */



    @Inject @DefaultKSMLogger
    private Logger logger;



    @Inject @KSMObjectsOGMSessionFactory
    private SessionFactory neo4jOgmSessionFactory;

    private Session ogmSession;


    /*
    @Inject
    public Neo4JEntityManagerBean( @KSMObjectsOGMSessionFactory SessionFactory sf) {
        if(sf == null){
            throw new IllegalArgumentException("Parameter SessionFactory must not be null");
        }
        this.ogmSession=sf.openSession();
    }
    */

    public Neo4JEntityManagerBean() {
    }

    @PostConstruct
    private void initNeo4JEntityManagerBean(){
    }
    @PreDestroy
    private void disposeNeo4JEntityManagerBean(){
        this.ogmSession.clear();
    }


}
