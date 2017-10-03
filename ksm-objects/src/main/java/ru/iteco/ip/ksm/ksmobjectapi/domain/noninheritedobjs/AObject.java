package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.*;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
//@NodeEntity(label = "KSMObject")
public abstract class AObject implements IAObject ,EditableAObj,Serializable {
    @Inject @DefaultKSMLogger
    protected Logger logger;

    @GraphId
    protected Long id;
    @Property(name="ksmObjId")
    @Id
    @Index(unique = true , primary = true)
    //@GeneratedValue(strategy = org.neo4j.ogm.id.UuidStrategy.class)
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;
    protected String debugInfo;


    protected String name;

    public AObject() {
    }

    /* getters and setters here*/
    @Override
    public Long getId() {
        return id;
    }


    @Override
    public String getKsmObjId() {
        return ksmObjId;
    }


    @Override
    public KSMObjectType getKsmObjType() {
        return ksmObjType;
    }
    @Override
    public void setKsmObjType(KSMObjectType ksmObjType) {
        this.ksmObjType = ksmObjType;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setKsmObjId(String ksmObjId) {
        this.ksmObjId = ksmObjId;
    }

    @Override
    public String getDebugInfo() {
        return debugInfo;
    }

    @Override
    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }
}
