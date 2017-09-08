package ru.iteco.ip.ksm.ksmobjectapi.domain.objects;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.relationshiptypes.KSMIndicatorToCiRelationType_ENUM;


/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public  class BaseKSMIndicator<T extends BaseKSMIndicator<T>>
        extends BaseKSMObject<BaseKSMIndicator<T>> {
    protected String name;
    protected String description;
    protected String value;
    protected String status;
    protected KSMIndicatorType indicatorType;
    protected KSMIndicatorToCiRelationType_ENUM ksmIndicatorToCiRelationType;


    public BaseKSMIndicator(){
        super();
        this.ksmObjType = KSMObjectType.INDICATOR;
    }

    public KSMIndicatorType getIndicatorType(){
        return this.indicatorType;
    }

    public KSMIndicatorToCiRelationType_ENUM getKsmIndicatorToCiRelationType() {
        return ksmIndicatorToCiRelationType;
    }
}
