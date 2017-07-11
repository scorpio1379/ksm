package ru.iteco.ip.ksm.ksmobjects.abstracts.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjects.relationships.CI2CIRelationShipType;
import ru.iteco.ip.ksm.ksmobjects.relationships.KSMRelationShipType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;

/**
 * Created by Administrator on 07.07.2017.
 */
@RelationshipEntity(type="KSM_BASE_RELATION_CI_TO_CI")
public abstract class BaseCI2CIRelationShip<CI2CIRelationShip extends BaseCI2CIRelationShip<CI2CIRelationShip>>
        extends KSMBaseRelationShip<BaseCI2CIRelationShip<CI2CIRelationShip>> {
    protected CI2CIRelationShipType ci2ciRelationShipType;
    public BaseCI2CIRelationShip() {
        this.ksmRelationShipType = KSMRelationShipType.CI_TO_CI;
    }

    public void setBaseCI2CIRelationShipType(CI2CIRelationShipType ci2CIRelationShipType){
        this.ci2ciRelationShipType = ci2CIRelationShipType;
    }
}
