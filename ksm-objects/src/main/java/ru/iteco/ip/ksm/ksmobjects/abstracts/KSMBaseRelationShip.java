package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.*;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_BASE_RELATION")
public abstract class KSMBaseRelationShip<T extends KSMBaseRelationShip<T>> extends KSMBaseObject{

    @StartNode
    public  KSMBaseObject startNode;
    @EndNode
    public KSMBaseObject endNode;


    public KSMBaseRelationShip(String uuid) {
        super(uuid);
    }

    public abstract void setStartNode(KSMBaseObject strtNode);
    public abstract void setEndNode (KSMBaseObject endNode);

}
