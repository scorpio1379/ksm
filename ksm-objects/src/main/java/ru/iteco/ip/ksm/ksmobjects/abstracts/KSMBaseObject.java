package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import ru.iteco.ip.ksm.ksmobjects.KSMObjectType;

import java.util.UUID;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public abstract class KSMBaseObject {
    @GraphId
    private Long id;
    @Property(name="ksmObjId")
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;
    public KSMBaseObject() {
        this.ksmObjId =  UUID.randomUUID().toString();
    }

    public String getKSMObjId(){
        String fullId = this.ksmObjId;
        return fullId;

    }

    public KSMObjectType getKSMObjType(){
        return this.ksmObjType;
    }

    public Long getId() {
        return id;
    }
}
