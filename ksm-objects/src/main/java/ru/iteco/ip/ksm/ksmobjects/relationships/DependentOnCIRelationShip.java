package ru.iteco.ip.ksm.ksmobjects.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseRelationShip;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_DEPENDENT_ON_RELATION")
public class DependentOnCIRelationShip<T extends DependentOnCIRelationShip<T>> extends KSMBaseRelationShip<T> {
    public DependentOnCIRelationShip(String uuid) {
        super(uuid);
    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {

    }

    public void setStartNode(KSMBaseCI<?> strtNode){
        this.startNode = strtNode;
    }
    public void setEndNode (KSMBaseCI<?> endNode){
        this.endNode = endNode;
    }
}