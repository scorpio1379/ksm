package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.IAObject;

/**
 * Created by Scorpio on 18.09.2017.
 */
public interface LinkedCIRelationShip extends IAObject {
    CI getStartci();

    void setStartci(CI startci);

    CI getEnddci();

    void setEnddci(CI enddci);
}
