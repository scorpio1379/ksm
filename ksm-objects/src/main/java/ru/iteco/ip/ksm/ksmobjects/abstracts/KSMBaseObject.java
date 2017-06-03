package ru.iteco.ip.ksm.ksmobjects.abstracts;

import ru.iteco.ip.ksm.ksmobjects.KSMObjectType;

import java.util.UUID;

/**
 * Created by Scorpio on 02.06.2017.
 */

public abstract class KSMBaseObject {
    protected String ksmObjId;
    protected KSMObjectType ksmObjType;
    public KSMBaseObject() {
        this.ksmObjId = "ksmObjId:" + UUID.randomUUID().toString();
    }

    public String getKSMObjId(){
        String fullId = this.ksmObjId;
        return fullId.split(":")[1];

    }

    public KSMObjectType getKSMObjType(){
        return this.ksmObjType;
    }
}
