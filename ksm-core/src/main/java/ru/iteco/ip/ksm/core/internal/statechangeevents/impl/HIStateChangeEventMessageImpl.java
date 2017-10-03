package ru.iteco.ip.ksm.core.internal.statechangeevents.impl;

import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMHI;
import ru.iteco.ip.ksm.core.internal.statechangeevents.HIStateChangeEventMessage;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 30.09.2017.
 */
public class HIStateChangeEventMessageImpl
        extends IndicatorStateChangeEventMessageImpl<InternalKSMHI>
        implements HIStateChangeEventMessage{

    public HIStateChangeEventMessageImpl() {
        super();
        this.ksmIndicatorType = KSMIndicatorType.HI;
    }
}
