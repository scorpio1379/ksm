package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_BASE_RELATION_INDICATOR_TO_CI")
public class BaseIndicatorToCIRelationShip<T extends KSMBaseRelationShip<T>> extends KSMBaseRelationShip<T> {

    public BaseIndicatorToCIRelationShip(String uuid) {
        super(uuid);
    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {

    }

    public void setStartNode(KSMBaseIndicator strtNode){
        this.startNode = strtNode;
    }
    public void setEndNode (KSMBaseCI endNode){
        this.endNode = endNode;
    }
}
