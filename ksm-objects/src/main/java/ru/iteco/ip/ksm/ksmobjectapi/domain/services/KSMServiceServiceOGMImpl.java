package ru.iteco.ip.ksm.ksmobjectapi.domain.services;

import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMService;
import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseCI2CIKSMRelationShip;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.abstracts.BaseKSMObjectServiceOGMImpl;

import java.util.Set;

/**
 * Created by Scorpio on 01.09.2017.
 */
public class KSMServiceServiceOGMImpl
        extends BaseKSMObjectServiceOGMImpl<KSMService>
        implements KSMServiceService
{
    @Override
    public Set<KSMCI> getAllRelatedCis() {
        return null;
    }

    @Override
    public Set<BaseCI2CIKSMRelationShip> getAllRelatedRelationShips() {
        return null;
    }

    @Override
    public Class<KSMService> getEntityType() {
        return KSMService.class;
    }
}
