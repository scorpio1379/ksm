package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;

import java.util.Set;

/**
 * Created by Scorpio on 22.09.2017.
 */
public interface ServiceModel {
    void addCI(CI ci);

    void addLinkedCIRelationShip(LinkedCIRelationShip linkedCIRelationShip);

    Set<CI> getRelatedCis();

    Set<LinkedCIRelationShip> getCi2ciRelationShips();

    void setRelatedCis(Set<CI> relatedCis);

    void setCi2ciRelationShips(Set<LinkedCIRelationShip> ci2ciRelationShips);
}
