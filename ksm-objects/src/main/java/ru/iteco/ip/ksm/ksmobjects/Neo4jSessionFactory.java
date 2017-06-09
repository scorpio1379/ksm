package ru.iteco.ip.ksm.ksmobjects;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Scorpio on 03.06.2017.
 */

public class Neo4jSessionFactory {
    private final static SessionFactory sessionFactory = new SessionFactory("ru.iteco.ip.ksm.ksmobjects");
    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    // prevent external instantiation
    private Neo4jSessionFactory() {
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }
}
