package ru.iteco.ip.ksm.ksmobjectapi.domain.objects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.KSMObjectType;

/**
 * Created by Scorpio on 07.09.2017.
 */
public interface IBaseKSMObject {
    KSMObjectType getKSMObjType();

    Long getId();

    String getKsmObjId();
}
