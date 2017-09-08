package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import org.neo4j.ogm.annotation.NodeEntity;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

/**
 * Created by Scorpio on 05.09.2017.
 */
@NodeEntity
public class Service_Obj extends CI_Obj implements Service{
    public Service_Obj() {
        super();
        this.ksmCiType = KSMCIType.SERVICE;
    }
}
