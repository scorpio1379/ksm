package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.*;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
public abstract class AObject implements IAObject ,EditableAObj {
    @Inject @DefaultKSMLogger
    protected Logger logger;

    @GraphId
    protected Long id;
    @Property(name="ksmObjId") @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;


    @Index(unique=true)
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
}
