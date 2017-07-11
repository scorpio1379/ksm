package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 07.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceKPI extends ServiceIndicator<ServiceKPI> {
    @JsonProperty
    public String kpiType;
    @JsonProperty
    public String ruleFileName;

    public ServiceKPI() {
        super();
        this.indicatorType = "KPI";
    }
}
