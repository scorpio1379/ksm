package ru.iteco.ip.ksm.ksmobjectapi;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import ru.iteco.ip.ksm.ksmobjectapi.domain.annotations.KSMObjectsOGMSession;
import ru.iteco.ip.ksm.ksmobjectapi.domain.annotations.KSMObjectsOGMSessionFactory;
import ru.iteco.ip.ksm.shared.configmanagers.KSMConfigurationManager;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.Neo4JGDBConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;


/**
 * Created by Scorpio on 03.06.2017.
 */
@Singleton
@Startup
@DependsOn("KSMCoreInitializerEJB")
@Lock(LockType.READ)
public class Neo4jSessionFactory {
    //private final static SessionFactory sessionFactory = new SessionFactory("ru.iteco.ip.ksm.ksmobjectsBackUp");
    //private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    @Inject
    @Default
    private KSMConfigurationManager ksmCfgMgr;

    private  SessionFactory sessionFactory ;



    public Neo4jSessionFactory() {
    }

    @PostConstruct
    private void initNeo4jSessionFactory(){
        int bltPortNum = ((Neo4JGDBConfiguration) (ksmCfgMgr.getGDBConfiguration())).getBoltProtocolNumber();
        Configuration bltCfg = new Configuration.Builder().uri("bolt://localhost:" + bltPortNum).autoIndex("assert").build();
        this.sessionFactory = new SessionFactory( bltCfg ,"ru.iteco.ip.ksm.ksmobjectapi");


    }
    @PreDestroy
    private void disposeNeo4jSessionFactory(){
        this.sessionFactory.close();
    }

    @Produces @KSMObjectsOGMSession
    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }

    @Produces @KSMObjectsOGMSessionFactory
    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }
}