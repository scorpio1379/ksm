package ru.iteco.ip.ksm.web.rest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 07.07.2017.
 */
public class ServiceModelRelationShipImpl implements Serializable {
    @JsonProperty
    public String startNodeId;
    @JsonProperty
    public String endNodeId;
    @JsonProperty
    public String relationType;

    @JsonCreator
    public ServiceModelRelationShipImpl(
            @JsonProperty("startCiTmpId") String startNodeId
            ,@JsonProperty("endCiTmpId") String endNodeId
            ,@JsonProperty("serviceRelationType") String relationType) {
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.relationType = relationType;
    }
}
