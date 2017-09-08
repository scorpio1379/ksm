package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface EditableAObj  {
    void setKsmObjType(KSMObjectType ksmObjType);

    void setName(String name);


    Long getId();

    String getKsmObjId();

    KSMObjectType getKsmObjType();


    String getName();

    void setKsmObjId(String ksmObjId);
}
