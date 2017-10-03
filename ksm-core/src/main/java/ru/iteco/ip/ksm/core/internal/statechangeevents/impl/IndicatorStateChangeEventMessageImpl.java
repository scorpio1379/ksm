package ru.iteco.ip.ksm.core.internal.statechangeevents.impl;

import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMIndicator;
import ru.iteco.ip.ksm.core.internal.statechangeevents.IndicatorStateChangeEventMessage;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 30.09.2017.
 */
public abstract class IndicatorStateChangeEventMessageImpl<INDICATORTYPE extends InternalKSMIndicator<INDICATORTYPE>>
        implements IndicatorStateChangeEventMessage<INDICATORTYPE> {
    protected String ksmObjId;
    protected String status;
    protected String value;
    protected String chgTimeStamp;
    protected KSMIndicatorType ksmIndicatorType;

    @Override
    public String getKsmObjId() {
        return ksmObjId;
    }
    @Override
    public void setKsmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
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
    public String getValue() {
        return value;
    }
    @Override
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String getChgTimeStamp() {
        return chgTimeStamp;
    }
    @Override
    public void setChgTimeStamp(String chgTimeStamp) {
        this.chgTimeStamp = chgTimeStamp;
    }
}
