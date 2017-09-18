package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;

/**
 * Created by Scorpio on 05.09.2017.
 */
@RelationshipEntity(type = "AttachedIndicator")
public class AttachedIndicator extends AObject {
    @StartNode
    private AIndicator indicator;
    @EndNode
    private CI ci;

    public AIndicator getIndicator() {
        return indicator;
    }

    public void setIndicator(AIndicator indicator) {
        this.indicator = indicator;
    }

    public CI getCi() {
        return ci;
    }

    public void setCi(CI ci) {
        this.ci = ci;
    }
}
