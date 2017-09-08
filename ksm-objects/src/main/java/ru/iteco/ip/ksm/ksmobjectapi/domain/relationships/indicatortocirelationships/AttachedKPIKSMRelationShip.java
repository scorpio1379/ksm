package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.indicatortocirelationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseIndicatorToCIRelationShip;


/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="KPI_TO_CI_KSM_RELATIONSHIP")
public class AttachedKPIKSMRelationShip<STARTNODE extends KSMKPI<STARTNODE>, ENDNODE extends KSMCI<ENDNODE>>
        extends BaseIndicatorToCIRelationShip< KSMKPI<STARTNODE> , KSMCI<ENDNODE>> {
    protected String kpiToCiKsmRelationShipType;
    public AttachedKPIKSMRelationShip() {
        super();
    }

}
