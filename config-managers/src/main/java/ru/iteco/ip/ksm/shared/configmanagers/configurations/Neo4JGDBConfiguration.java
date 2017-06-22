package ru.iteco.ip.ksm.shared.configmanagers.configurations;

import ru.iteco.ip.ksm.shared.configmanagers.GDBType;
import ru.iteco.ip.ksm.shared.configmanagers.configurations.abstracts.BaseGDBConfiguration;

/**
 * Created by Scorpio on 11.06.2017.
 */
public class Neo4JGDBConfiguration extends BaseGDBConfiguration<Neo4JGDBConfiguration>{
    private String neo4jDBPath;
    private int boltProtocolNumber;

    public Neo4JGDBConfiguration(String neo4jDBPath, int boltProtocolNumber) {
        super();
        this.neo4jDBPath = neo4jDBPath;
        this.boltProtocolNumber = boltProtocolNumber;
        this.gdbType = GDBType.Neo4J;
    }

    public Neo4JGDBConfiguration() {
        super();
        this.gdbType = GDBType.Neo4J;
    }

    public String getNeo4jDBPath() {
        return neo4jDBPath;
    }

    public void setNeo4jDBPath(String neo4jDBPath) {
        this.neo4jDBPath = neo4jDBPath;
    }

    public int getBoltProtocolNumber() {
        return boltProtocolNumber;
    }

    public void setBoltProtocolNumber(int boltProtocolNumber) {
        this.boltProtocolNumber = boltProtocolNumber;
    }



}
