package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

/**
 * Created by Scorpio on 05.09.2017.
 */

public class Service_Obj extends CI_Obj implements Service{
    private String serviceType;
    public Service_Obj() {
        super();
        this.ksmCiType = KSMCIType.SERVICE;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String servicetype) {
        this.serviceType = servicetype;
    }
}
