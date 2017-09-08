package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMIndicatorType;

/**
 * Created by Scorpio on 07.09.2017.
 */
public interface KPIBuilder extends ObjectBuilder<KPIBuilder , KPI> {

    /* общие для ksm indicator*/
    KPIBuilder description (String description);
    KPIBuilder value (String value);
    KPIBuilder status (String status);
    KPIBuilder ksmIndicatorType (KSMIndicatorType ksmIndicatorType);

    /* общие для KSMKPI*/
    KPIBuilder kpiType  (String kpiType);
    KPIBuilder calcRuleId (String calcRuleId);



}
