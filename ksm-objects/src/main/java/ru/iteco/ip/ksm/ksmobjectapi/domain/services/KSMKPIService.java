package ru.iteco.ip.ksm.ksmobjectapi.domain.services;


import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.indicators.KSMKPIType;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMObjectService;

import javax.ejb.Local;

/**
 * Created by Scorpio on 12.07.2017.
 */
@Local
public interface KSMKPIService  extends BaseKSMObjectService<KSMKPI> {
    KSMKPI createKSMKPIAtKSMCI(KSMCI ci, String ksmKpiObjId, String name, KSMKPIType kpiType);
}
