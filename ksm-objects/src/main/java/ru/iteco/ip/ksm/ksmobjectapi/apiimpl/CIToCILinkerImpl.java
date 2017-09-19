package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.CIToCILinker;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.LinkedCIRelationShipSrvc;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Scorpio on 14.09.2017.
 */
@Stateless
public class CIToCILinkerImpl implements CIToCILinker {
    @Inject
    @DefaultKSMLogger
    private Logger logger;

    @Inject
    private LinkedCIRelationShipSrvc linkedCIRelationShipSrvc;

    private CI startCiNode;
    private CI endCiNode;
    private String ciTociRelationType;

    @Override
    public CIToCILinker setStartCI(CI startCi) {
        this.startCiNode = startCi;
        return this;
    }

    @Override
    public CIToCILinker setEndCI(CI endCi) {
        this.endCiNode = endCi;
        return this;
    }

    @Override
    public CIToCILinker setLinkType(String linkType) {
        this.ciTociRelationType = linkType;
        return this;
    }

    @Override
    public CIToCILinker setStartCI(String startCiKsmObjId) {
        return null;
    }

    @Override
    public CIToCILinker setEndCI(String endCiKsmObjId) {
        return null;
    }

    @Override
    public void build() {
        /*TODO: придумать как реализовать иерархичное создание связей по произвольному тексту linkType*/
        if ((startCiNode!=null) && (endCiNode!=null)) {
            try {
                linkedCIRelationShipSrvc.createLinkedCIRelationShip(startCiNode , endCiNode);
            } catch (InstantiationException| IllegalAccessException e) {
                //e.printStackTrace();
                logger.error("Cant create link by LinkedCIRelationShipSrvc  from {} to {} with error {}" , startCiNode.getKsmObjId() , endCiNode.getKsmObjId(), e );
            }
        }


    }

}
