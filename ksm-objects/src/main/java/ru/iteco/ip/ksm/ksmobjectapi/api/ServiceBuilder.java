package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

import java.util.Set;

/**
 * Created by Scorpio on 10.09.2017.
 */
public interface ServiceBuilder extends ObjectBuilder<ServiceBuilder , Service> {
    ServiceBuilder description(String description);

    ServiceBuilder ksmCiType(KSMCIType ksmCiType);

    ServiceBuilder statusKPIksmObjId(String statusKPIksmObjId);

    Set<Service> getAll();

    ServiceModel getServiceModel(Service service);
}
