package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 05.09.2017.
 */
@RelationshipEntity(type = "LinkedCI")
public class LinkedCIRelationShipImpl extends ARelationShip<CI ,CI> implements LinkedCIRelationShip {


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
