package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;

/**
 * Created by Scorpio on 18.09.2017.
 */
public interface LinkedCIRelationShipSrvc extends AbstractSrvc<LinkedCIRelationShip>{
    LinkedCIRelationShip createLinkedCIRelationShip(CI startCiNode, CI endCiNode) throws InstantiationException, IllegalAccessException;
}
