package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import ru.iteco.ip.ksm.ksmobjectapi.api.ServiceModel;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scorpio on 22.09.2017.
 */
public class ServiceModelImpl implements ServiceModel {
    private Set<CI> relatedCis = new HashSet<>();
    private Set<LinkedCIRelationShip> ci2ciRelationShips = new HashSet<>();

    @Override
    public void addCI(CI ci){
        boolean isSuccesfull = this.relatedCis.add(ci);

    }
    @Override
    public void addLinkedCIRelationShip(LinkedCIRelationShip linkedCIRelationShip){
        boolean isSuccesfull = this.ci2ciRelationShips.add(linkedCIRelationShip);

    }
    @Override
    public Set<CI> getRelatedCis() {
        return relatedCis;
    }

    @Override
    public Set<LinkedCIRelationShip> getCi2ciRelationShips() {
        return ci2ciRelationShips;
    }

    @Override
    public void setRelatedCis(Set<CI> relatedCis) {
        this.relatedCis = relatedCis;
    }

    @Override
    public void setCi2ciRelationShips(Set<LinkedCIRelationShip> ci2ciRelationShips) {
        this.ci2ciRelationShips = ci2ciRelationShips;
    }
}
