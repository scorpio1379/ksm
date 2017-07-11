package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 07.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceHI extends ServiceIndicator<ServiceHI>{
    @JsonProperty
    public String itemId;
    @JsonProperty
    public String hiType;

    public ServiceHI() {
        super();
        this.indicatorType = "HI";
    }
}
