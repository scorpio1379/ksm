package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;


import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.LinkedCIRelationShip;

import java.util.Set;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface ServiceSrvc extends AbstractSrvc<Service> {
    Set<CI> getAllRelatedCis(Service service);
    Set<LinkedCIRelationShip> getAllRelations(Service service);
}
