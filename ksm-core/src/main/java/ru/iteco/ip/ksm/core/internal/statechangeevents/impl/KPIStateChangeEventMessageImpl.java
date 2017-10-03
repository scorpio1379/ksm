package ru.iteco.ip.ksm.core.internal.statechangeevents.impl;

import ru.iteco.ip.ksm.core.internal.ksmobjects.InternalKSMKPI;
import ru.iteco.ip.ksm.core.internal.statechangeevents.KPIStateChangeEventMessage;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 30.09.2017.
 */
public class KPIStateChangeEventMessageImpl
        extends IndicatorStateChangeEventMessageImpl<InternalKSMKPI>
        implements KPIStateChangeEventMessage {

    public KPIStateChangeEventMessageImpl() {
        super();
        this.ksmIndicatorType = KSMIndicatorType.KPI;
    }
}
