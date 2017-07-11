package ru.iteco.ip.ksm.ksmobjects.abstracts.relationships;

import org.neo4j.ogm.annotation.*;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.relationships.KSMRelationShipType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_BASE_RELATION")
public abstract class KSMBaseRelationShip<T extends KSMBaseRelationShip<T>> {

    @Inject @DefaultKSMLogger
    Logger logger;

    @StartNode
    public KSMBaseObject startNode;
    @EndNode
    public KSMBaseObject endNode;

    @Property(name="ksmRelationShipType")
    protected KSMRelationShipType ksmRelationShipType;


    public KSMBaseRelationShip() {

    }


    public abstract void setStartNode(KSMBaseObject strtNode);
    public abstract void setEndNode (KSMBaseObject endNode);

    public abstract void setStartNode(String startObjId);
    public abstract void setEndNode(String startObjId);

}
