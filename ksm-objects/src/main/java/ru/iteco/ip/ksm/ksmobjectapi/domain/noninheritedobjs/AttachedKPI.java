package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;

import java.io.Serializable;

/**
 * Created by Scorpio on 05.09.2017.
 */
@RelationshipEntity(type = "AttachedKPI")
public class AttachedKPI implements Serializable {
    private Long id;
    @StartNode
    private KPI kpi;
    @EndNode
    private CI ci;

    public KPI getKpi() {
        return kpi;
    }

    public void setKpi(KPI kpi) {
        this.kpi = kpi;
    }

    public CI getCi() {
        return ci;
    }

    public void setCi(CI ci) {
        this.ci = ci;
    }
}
