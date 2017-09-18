package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;

/**
 * Created by Scorpio on 05.09.2017.
 */

public class Service_Obj extends CI_Obj implements Service{
    private String servicetype;
    public Service_Obj() {
        super();
        this.ksmCiType = KSMCIType.SERVICE;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }
}
