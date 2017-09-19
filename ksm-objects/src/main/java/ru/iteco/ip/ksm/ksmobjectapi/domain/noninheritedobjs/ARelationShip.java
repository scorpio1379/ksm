package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.*;
import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

import java.io.Serializable;

/**
 * Created by Scorpio on 18.09.2017.
 */
public class   ARelationShip<STARTNODE extends IAObject, ENDNODE extends IAObject > implements IAObject,Serializable {
    @GraphId
    protected Long id;
    @StartNode
    protected STARTNODE startci;
    @EndNode
    protected ENDNODE enddci;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getKsmObjId() {
        throw new IllegalArgumentException("Relation has no KSM ID");
        //if (this.id!=null){
        //    return this.id.toString();
        //}else{
        //    return null;
        //}
    }

    @Override
    public KSMObjectType getKsmObjType() {
        //throw new IllegalArgumentException("Relation has no KsmObjType");
        return KSMObjectType.RELATIONSHIP;
    }

    @Override
    public String getName() {
        //throw new IllegalArgumentException("Relation has no name");
        return "Relation has no name";
    }

    @Override
    public void setName(String name) {

    }
}
