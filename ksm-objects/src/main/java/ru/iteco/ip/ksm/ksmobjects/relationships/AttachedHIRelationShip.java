package ru.iteco.ip.ksm.ksmobjects.relationships;

import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseObject;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseRelationShip;

/**
 * Created by Scorpio on 20.06.2017.
 */
public class AttachedHIRelationShip<T extends KSMBaseRelationShip<T>> extends KSMBaseRelationShip<AttachedHIRelationShip<T>> {

    public AttachedHIRelationShip(String uuid) {
        super(uuid);
    }

    @Override
    public void setStartNode(KSMBaseObject strtNode) {

    }

    @Override
    public void setEndNode(KSMBaseObject endNode) {

    }

}
