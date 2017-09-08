package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 07.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceCIImpl implements Serializable {
    @JsonProperty
    public String tmpId;
    @JsonProperty
    public String displayName;
    @JsonProperty
    public String name;
    @JsonProperty
    public String serviceType;
    @JsonProperty
    public String description;
    @JsonProperty
    public String status_kpi_id;
    @JsonProperty("Lvl")
    public String lvl;
    @JsonProperty("Properties")
    public List<Object> properties;
    @JsonProperty("HIs")
    public List<ServiceHI> his;
    @JsonProperty("KPIs")
    public List<ServiceKPI> kpis;

    public ServiceCIImpl() {
    }
}
