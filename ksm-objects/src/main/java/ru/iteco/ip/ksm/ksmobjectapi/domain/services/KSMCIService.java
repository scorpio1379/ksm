package ru.iteco.ip.ksm.ksmobjectapi.domain.services;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.IKSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMCIService;

import javax.ejb.Local;

/**
 * Created by Scorpio on 21.06.2017.
 */
@Local
public interface KSMCIService
        extends BaseKSMCIService<IKSMCI> {

    KSMCI getCIByKsmObjId(String ciKsmObjId);
}
