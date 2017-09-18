package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

import java.util.Set;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface EditableCI extends EditableAObj {
    void setDescription(String description);

    void setKsmCiType(KSMCIType ksmCiType);

    void setStatusKPIksmObjId(String statusKPIksmObjId);

    void setAttachedHISet(Set<AttachedHI> attachedHISet);

    void setAttachedKPISet(Set<AttachedKPI> attachedKPISet);

    void setDependentCi(Set<LinkedCIRelationShip> dependentCi);


    String getDescription();


    KSMCIType getKsmCiType();


    String getStatusKPIksmObjId();


    Set<AttachedHI> getAttachedHISet();


    Set<AttachedKPI> getAttachedKPISet();


    Set<LinkedCIRelationShip> getDependentCi();
}
