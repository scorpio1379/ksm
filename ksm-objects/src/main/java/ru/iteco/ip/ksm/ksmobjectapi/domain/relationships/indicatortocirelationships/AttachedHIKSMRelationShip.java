package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.indicatortocirelationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseIndicatorToCIRelationShip;


/**
 * Created by Scorpio on 20.06.2017.
 */
@RelationshipEntity(type="HI_TO_CI_KSM_RELATIONSHIP")
public class AttachedHIKSMRelationShip<U extends KSMHI<U>, V extends KSMCI<V> >
        extends BaseIndicatorToCIRelationShip<KSMHI<U>,KSMCI<V>> {
    protected String hiToCiKsmRelationShipType;

    public AttachedHIKSMRelationShip() {
        super();
    }


}
