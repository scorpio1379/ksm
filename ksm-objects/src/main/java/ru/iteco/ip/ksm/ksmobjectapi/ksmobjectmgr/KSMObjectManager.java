package ru.iteco.ip.ksm.ksmobjectapi.ksmobjectmgr;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMService;

import java.util.Set;

/**
 * Created by Scorpio on 01.09.2017.
 */
public interface KSMObjectManager {
    Set<KSMService<?>> getAllKSMServices();
}
