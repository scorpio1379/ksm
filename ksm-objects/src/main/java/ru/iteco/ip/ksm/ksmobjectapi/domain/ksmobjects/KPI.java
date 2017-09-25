package ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.AttachedKPI;

/**
 * Created by Scorpio on 04.09.2017.
 */
public interface KPI extends IAIndicator {
    String getKpiType();

    void setKpiType(String kpiType);

    String getKpiCalcRuleId();

    void setKpiCalcRuleId(String kpiCalcRuleId);

    AttachedKPI getAttachedKPIRelationShip();

    void setAttachedKPIRelationShip(AttachedKPI attachedKPIRelationShip);
}
