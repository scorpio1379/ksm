package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;

import java.io.Serializable;

/**
 * Created by Scorpio on 05.09.2017.
 */
@RelationshipEntity(type = "AttachedHI")
public class AttachedHI implements Serializable{
    private Long id;
    @StartNode
    private HI hi;
    @EndNode
    private CI ci;


    public HI getHi() {
        return hi;
    }

    public void setHi(HI hi) {
        this.hi = hi;
    }

    public CI getCi() {
        return ci;
    }

    public void setCi(CI ci) {
        this.ci = ci;
    }
}
