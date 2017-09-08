package ru.iteco.ip.ksm.ksmobjectapi.api;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface ObjectBuilder<BUILDERTYPE, OBJECTYPE > {

    BUILDERTYPE ksmObjId (String ksmObjId);
    BUILDERTYPE name(String name);
    OBJECTYPE build();
}
