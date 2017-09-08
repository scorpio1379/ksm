package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface CIBuilder extends ObjectBuilder<CIBuilder , CI> {


    CIBuilder description(String description);

    CIBuilder ksmCiType(KSMCIType ksmCiType);

    CIBuilder statusKPIksmObjId(String statusKPIksmObjId);

    CI build();



}
