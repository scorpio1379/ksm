package ru.iteco.ip.ksm.ksmobjects.relationships;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.BaseIndicatorToCIRelationShip;
import ru.iteco.ip.ksm.ksmobjects.abstracts.relationships.KSMBaseRelationShip;

/**
 * Created by Scorpio on 20.06.2017.
 */
public class AttachedHIRelationShip<T extends AttachedHIRelationShip<T>>
        extends BaseIndicatorToCIRelationShip<AttachedHIRelationShip<T>> {

    public AttachedHIRelationShip() {
    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {

    }

}
