package ru.iteco.ip.ksm.ksmobjects.abstracts.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;

/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KSM_BASE_RELATION_INDICATOR_TO_CI")
public class BaseIndicatorToCIRelationShip<T extends BaseIndicatorToCIRelationShip<T>>
        extends KSMBaseRelationShip<BaseIndicatorToCIRelationShip<T>> {

    public BaseIndicatorToCIRelationShip() {
        super();
    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {
        /*TODO: реализовать метод с проверкой, что startNode является обьектом типа KSMIbdicator*/

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {
        /*TODO: реализовать метод с проверкой, что startNode является обьектом типа KSMBaseCI*/

    }

    @Override
    public void setStartNode(String startObjId) {
         /*TODO: реализовать метод с проверкой, что startNode является обьектом типа KSMIbdicator*/
    }

    @Override
    public void setEndNode(String startObjId) {
        /*TODO: реализовать метод с проверкой, что startNode является обьектом типа KSMBaseCI*/

    }

    public void setStartNode(KSMBaseIndicator strtNode){
        this.startNode = strtNode;
    }
    public void setEndNode (KSMBaseCI endNode){
        this.endNode = endNode;
    }
}
