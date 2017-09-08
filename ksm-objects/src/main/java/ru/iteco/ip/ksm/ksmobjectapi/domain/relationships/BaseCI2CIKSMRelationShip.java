package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMCI;


/**
 * Created by Administrator on 07.07.2017.
 */
@RelationshipEntity(type="CI_TO_CI_KSM_RELATIONSHIP")
public class BaseCI2CIKSMRelationShip<STARTNODE extends BaseKSMCI<STARTNODE>,ENDNODE extends BaseKSMCI<ENDNODE>>
        extends BaseKSMRelationShip<BaseKSMCI<STARTNODE> , BaseKSMCI<ENDNODE>> {

    protected String ci2ciRelationShipType;

    public BaseCI2CIKSMRelationShip() {
        super();
    }
}
