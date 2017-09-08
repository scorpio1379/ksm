package ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis;

import ru.iteco.ip.ksm.ksmobjectapi.domain.relationships.BaseCI2CIKSMRelationShip;

import java.util.Set;

/**
 * Created by Scorpio on 31.08.2017.
 */
public class KSMService<T extends KSMService<T>> extends KSMCI<KSMService<T>> implements IKSMService {

    private Set<KSMCI> cis;
    private Set<BaseCI2CIKSMRelationShip> relationships;

    public KSMService() {
        super();
    }

}
