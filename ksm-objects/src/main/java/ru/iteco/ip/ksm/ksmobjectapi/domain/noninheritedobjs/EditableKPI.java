package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

/**
 * Created by Scorpio on 07.09.2017.
 */
public interface EditableKPI extends EditableIAIndicator {
    String getKpiType();

    void setKpiType(String kpiType);

    String getKpiCalcRuleId();

    void setKpiCalcRuleId(String kpiCalcRuleId);
}
