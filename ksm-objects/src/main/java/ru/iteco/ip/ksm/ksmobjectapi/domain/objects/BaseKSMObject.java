package ru.iteco.ip.ksm.ksmobjectapi.domain.objects;

import org.neo4j.ogm.annotation.*;
import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Scorpio on 02.06.2017.
 */
@NodeEntity
public abstract class BaseKSMObject<T extends BaseKSMObject<T>> implements Serializable , IBaseKSMObject{
    @GraphId
    private Long id;
    @Property(name="ksmObjId") @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Index(unique = true , primary = true)
    //@GeneratedValue(strategy = org.neo4j.ogm.id.UuidStrategy.class)
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;

    @Inject @DefaultKSMLogger
    protected Logger logger;

    public BaseKSMObject() {
    }



    @Override
    public KSMObjectType getKSMObjType(){
        return this.ksmObjType;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public String getKsmObjId(){ return this.ksmObjId;}
}
