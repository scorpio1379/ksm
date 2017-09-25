package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAIndicator;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
//@NodeEntity(label = "KSMIndicator")
public abstract class AIndicator extends AObject implements IAIndicator , EditableIAIndicator {
    protected String value;
    protected String status;
    protected KSMIndicatorType indicatorType;
    protected String description;


    public AIndicator() {
        super();
        this.ksmObjType = KSMObjectType.INDICATOR;
    }
    @Override
    public String getValue() {
        return value;
    }
    @Override
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public KSMIndicatorType getIndicatorType() {
        return indicatorType;
    }
    @Override
    public void setIndicatorType(KSMIndicatorType indicatorType) {
        this.indicatorType = indicatorType;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
