package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMObject;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="BASE_KSM_RELATIONSHIP")
public abstract class BaseKSMRelationShip<U extends BaseKSMObject<U>, V extends BaseKSMObject<V> > {


    @StartNode
    protected U startNode;
    @EndNode
    protected V endNode;



    public BaseKSMRelationShip() {
        //this.ksmObjType = KSMObjectType.RELATIONSHIP;

    }

    public U getStartNode() {
        return startNode;
    }

    public void setStartNode(U startNode) {
        this.startNode = startNode;
    }

    public V getEndNode() {
        return endNode;
    }

    public void setEndNode(V endNode) {
        this.endNode = endNode;
    }
}
