package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 04.07.2017.
 */
@JacksonXmlRootElement(localName = "ServiceModel")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KSMServiceModelImpl implements KSMServiceModel , Serializable {
    @JacksonXmlProperty
    @JsonProperty
    public String name;
    @JacksonXmlProperty
    @JsonProperty
    public String tmpId;
    @JacksonXmlProperty
    @JsonProperty
    public String displayName;
    @JacksonXmlProperty
    @JsonProperty
    public String serviceType;
    @JacksonXmlProperty
    @JsonProperty
    public String description;
    @JacksonXmlProperty
    @JsonProperty
    public String status_kpi_id;


    @JacksonXmlProperty
    @JsonProperty
    public List<ServiceKPI> kpis;

    @JacksonXmlProperty
    @JsonProperty
    public List<ServiceHI> his;


    @JsonProperty("Relationships")
    public List<ServiceModelRelationShipImpl> serviceModelRelationShips;
    @JsonProperty("Services")
    public List<ServiceCIImpl> serviceCIList;
    @JsonCreator
    public KSMServiceModelImpl(
            @JsonProperty("name") String name
            ,@JsonProperty("KPIs") List<ServiceKPI> kpis
            ,@JsonProperty("HIs") List<ServiceHI> his
            ,@JsonProperty("Services") List<ServiceCIImpl> serviceCIList
            ,@JsonProperty("Relationships") List<ServiceModelRelationShipImpl> relationShips) {
        this.name = name;
        this.kpis = kpis;
        this.his = his;
        this.serviceModelRelationShips = relationShips;
        this.serviceCIList = serviceCIList;
    }

    public KSMServiceModelImpl() {

    }

}
