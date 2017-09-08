package ru.iteco.ip.ksm.ksmobjectapi.domain.relationships;

import org.neo4j.ogm.annotation.RelationshipEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.BaseKSMIndicator;


/**
 * Created by Scorpio on 19.06.2017.
 */
@RelationshipEntity(type="INDICATOR_TO_CI_KSM_RELATIONSHIP")
public  class BaseIndicatorToCIRelationShip<U extends BaseKSMIndicator<U>,V extends BaseKSMCI<V>>
        extends BaseKSMRelationShip<BaseKSMIndicator<U>, BaseKSMCI<V>> {

    public String ksmIndicatorToCiRelationType;


    public BaseIndicatorToCIRelationShip() {
        super();

    }


}
