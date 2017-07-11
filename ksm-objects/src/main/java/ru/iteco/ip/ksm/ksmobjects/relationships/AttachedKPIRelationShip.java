package ru.iteco.ip.ksm.ksmobjects.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.BaseIndicatorToCIRelationShip;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.KSMBaseRelationShip;
import ru.iteco.ip.ksm.ksmobjects.indicators.KSMKPI;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_ATTACHED_KPI_RELATION")
public class AttachedKPIRelationShip<T extends AttachedKPIRelationShip<T>>
        extends BaseIndicatorToCIRelationShip<AttachedKPIRelationShip<T> > {
    public AttachedKPIRelationShip() {
        super();
    }

    public void setStartNode(KSMKPI<?> strtNode){
        this.startNode = strtNode;
    }
    public void setEndNode (KSMBaseCI endNode){
        this.endNode = endNode;
    }
}
