package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.citocirelationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseCI2CIKSMRelationShip;


/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="DEPENDS_ON_KSM_RELATIONSHIP")
public class DependentOnCIKSMRelationShip<STARTNODE extends KSMCI<STARTNODE>, ENDNODE extends KSMCI<ENDNODE>>
        extends BaseCI2CIKSMRelationShip<KSMCI<STARTNODE>,KSMCI<ENDNODE>> {
    protected String dependsOnKsmRelationShipType;

    public DependentOnCIKSMRelationShip() {
        super();
    }
}