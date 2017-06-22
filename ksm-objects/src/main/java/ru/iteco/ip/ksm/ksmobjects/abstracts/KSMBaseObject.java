package ru.iteco.ip.ksm.ksmobjects.abstracts;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import ru.iteco.ip.ksm.ksmobjects.KSMObjectType;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public abstract class KSMBaseObject<T> implements Serializable{
    @GraphId
    private Long id;
    @Property(name="ksmObjId") @Id
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;

    private KSMBaseObject() {
        this.ksmObjId =  UUID.randomUUID().toString();
    }

    public KSMBaseObject(String uuid) {
        this.ksmObjId =  uuid;
    }


    public KSMObjectType getKSMObjType(){
        return this.ksmObjType;
    }

    public Long getId() {
        return id;
    }

    public String getKsmObjId(){ return this.ksmObjId;}
}
