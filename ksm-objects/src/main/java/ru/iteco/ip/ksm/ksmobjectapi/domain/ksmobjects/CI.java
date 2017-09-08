package ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects;

import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.AttachedHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.AttachedKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

import java.util.Set;

/**
 * Created by Scorpio on 04.09.2017.
 */
public interface CI extends IAObject{



    String getDescription();


    KSMCIType getKsmCiType();


    String getStatusKPIksmObjId();


    Set<AttachedHI> getAttachedHISet();


    Set<AttachedKPI> getAttachedKPISet();


    Set<LinkedCI> getDependentCi();

}
