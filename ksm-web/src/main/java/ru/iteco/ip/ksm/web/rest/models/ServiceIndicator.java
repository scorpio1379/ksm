package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 07.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ServiceIndicator<T extends ServiceIndicator<T>> implements Serializable {
    @JsonProperty
    public String tmpId;
    @JsonProperty
    public String displayName;
    @JsonProperty
    public String name;

    protected String indicatorType;

    public ServiceIndicator() {
    }

    public ServiceIndicator(String tmpId, String displayName, String name) {
        this.tmpId = tmpId;
        this.displayName = displayName;
        this.name = name;
    }
}
