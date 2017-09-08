package ru.iteco.ip.ksm.ksmobjectapi.domain.services;


import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMHIType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMObjectService;

import javax.ejb.Local;

/**
 * Created by Scorpio on 12.07.2017.
 */
@Local
public interface KSMHIService
        extends BaseKSMObjectService<KSMHI> {
    KSMHI createKSMHIAtKSMCI(KSMCI<?> ci, String ksmHiObjId, String name, KSMHIType ksmhiType);
}
