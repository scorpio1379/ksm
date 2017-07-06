package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.iteco.ip.ksm.ksmobjects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjects.indicators.KSMKPI;
import ru.iteco.ip.ksm.ksmobjects.relationships.DependentOnCIRelationShip;

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
    public List<KSMKPI> kpis;
    @JsonCreator
    public KSMServiceModelImpl(
            @JsonProperty("name") String name
            ,@JsonProperty("KPIs") List<KSMKPI> kpis
            ,@JsonProperty("Services") List<KSMCI> cis
            ,@JsonProperty("Relationships") List<DependentOnCIRelationShip> relationShips) {
        this.name = name;
        this.kpis = kpis;
    }

    public KSMServiceModelImpl() {

    }
}
