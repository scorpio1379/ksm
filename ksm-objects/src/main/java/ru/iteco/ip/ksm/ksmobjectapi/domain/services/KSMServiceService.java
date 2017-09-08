package ru.iteco.ip.ksm.ksmobjectapi.domain.services;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMService;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseCI2CIKSMRelationShip;

import java.util.Set;

/**
 * Created by Scorpio on 01.09.2017.
 */
public  interface  KSMServiceService
        extends KSMCIServiceINHIF<KSMService> {
    Set<KSMCI> getAllRelatedCis();
    Set<BaseCI2CIKSMRelationShip> getAllRelatedRelationShips();
}
