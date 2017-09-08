package ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface IAObject {
    /* getters and setters here*/
    Long getId();

    String getKsmObjId();

    KSMObjectType getKsmObjType();

    //void setKsmObjType(KSMObjectType ksmObjType);

    String getName();

    //void setName(String name);
}
