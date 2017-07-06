package ru.iteco.ip.ksm.ksmobjects.indicators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import ru.iteco.ip.ksm.ksmobjects.KSMIndicatorType;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseCI;
import ru.iteco.ip.ksm.ksmobjects.abstracts.KSMBaseIndicator;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class KSMKPI<T extends KSMKPI<T>> extends KSMBaseIndicator<KSMKPI<T>> {
    private String ruleFileName;
    private String kpiType;
    private String rule;
    @Relationship(type = "ATTACHED_KPI")
    private KSMBaseCI attachedToCI;

    @JsonCreator
    public KSMKPI(
            @JsonProperty("tmpId") String uuid
            ,@JsonProperty("name") String name
            ,@JsonProperty("ruleFileName") String ruleFileName
            ,@JsonProperty("displayName") String displayName
            ,@JsonProperty("rule") String rule
            ,@JsonProperty("kpiType") String kpiType
    ) {
        super(uuid , name);
        this.ruleFileName = ruleFileName;
        this.rule = rule;
        this.kpiType = kpiType;
        this.indicatorType = KSMIndicatorType.KPI;
    }

    public KSMBaseCI getAttachedToCI() {
        return attachedToCI;
    }

    public void setAttachedToCI(KSMBaseCI attachedToCI) {
        this.attachedToCI = attachedToCI;
    }
}
