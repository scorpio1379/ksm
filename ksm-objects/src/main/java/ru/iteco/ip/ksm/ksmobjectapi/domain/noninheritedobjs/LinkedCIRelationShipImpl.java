package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 05.09.2017.
 */
@RelationshipEntity(type = "LinkedCI")
public class LinkedCIRelationShipImpl extends AObject implements LinkedCIRelationShip {
    @StartNode
    private CI startci;
    @EndNode
    private CI enddci;

    @Override
    public CI getStartci() {
        return startci;
    }
    @Override
    public void setStartci(CI startci) {
        this.startci = startci;
    }
    @Override
    public CI getEnddci() {
        return enddci;
    }
    @Override
    public void setEnddci(CI enddci) {
        this.enddci = enddci;
    }
}
