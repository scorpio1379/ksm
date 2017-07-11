package ru.iteco.ip.ksm.ksmobjects.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.BaseCI2CIRelationShip;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.KSMBaseRelationShip;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_DEPENDENT_ON_RELATION")
public class DependentOnCIRelationShip<T extends DependentOnCIRelationShip<T>> extends BaseCI2CIRelationShip<DependentOnCIRelationShip<T>> {

    public DependentOnCIRelationShip() {
        super();
        this.ci2ciRelationShipType =CI2CIRelationShipType.DEPENDS_ON;
    }

    public DependentOnCIRelationShip(String startNodeId , String endNodeId){
        super();
        this.ci2ciRelationShipType =CI2CIRelationShipType.DEPENDS_ON;


    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {

    }

    @Override
    public void setStartNode(String startObjId) {

    }

    @Override
    public void setEndNode(String startObjId) {

    }

    public void setStartNode(KSMBaseCI<?> strtNode){
        this.startNode = strtNode;
    }
    public void setEndNode (KSMBaseCI<?> endNode){
        this.endNode = endNode;
    }
}