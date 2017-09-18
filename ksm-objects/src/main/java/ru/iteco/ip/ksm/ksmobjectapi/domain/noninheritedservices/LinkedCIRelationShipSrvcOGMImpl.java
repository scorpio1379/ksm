package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShipImpl;

/**
 * Created by Scorpio on 18.09.2017.
 */
public class LinkedCIRelationShipSrvcOGMImpl extends AbstractSrvcOGMImpl<LinkedCIRelationShip> implements LinkedCIRelationShipSrvc {
    @Override
    public Class getEntityType() {
        return LinkedCIRelationShipImpl.class;
    }

    @Override
    public LinkedCIRelationShip createLinkedCIRelationShip(CI startCiNode, CI endCiNode) throws InstantiationException, IllegalAccessException {
        LinkedCIRelationShip tmpRel = this.instansiateFromEntityType();
        tmpRel.setStartci(startCiNode);
        tmpRel.setEnddci(endCiNode);
        LinkedCIRelationShip linkRel = this.createOrUpdate(tmpRel);
        return linkRel;
    }
}
