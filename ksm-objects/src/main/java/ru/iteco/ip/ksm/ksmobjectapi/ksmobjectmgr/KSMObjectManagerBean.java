package ru.iteco.ip.ksm.ksmobjectapi.ksmobjectmgr;

import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.CISrvc;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMService;
import ru.iteco.ip.ksm.ksmobjectapi.domain.services.KSMServiceService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Set;

/**
 * Created by Scorpio on 01.09.2017.
 */
@Stateless(name = "KSMObjectManagerEJB")
public class KSMObjectManagerBean implements KSMObjectManager{
    @Inject
    private CISrvc ksmciService;
    private KSMServiceService ksmServiceService;
    



    public KSMObjectManagerBean() {
    }

    @Override
    public Set<KSMService<?>> getAllKSMServices() {
        return null;
    }
}
