package ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.AttachedHI;

/**
 * Created by Scorpio on 04.09.2017.
 */
public interface HI extends IAIndicator {
    String getHiType();

    void setHiType(String hiType);

    AttachedHI getAttachedHIRelationShip();

    void setAttachedHIRelationShip(AttachedHI attachedHIRelationShip);
}
