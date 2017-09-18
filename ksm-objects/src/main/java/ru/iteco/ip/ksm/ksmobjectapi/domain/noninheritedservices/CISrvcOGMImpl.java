package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;


import org.apache.commons.lang3.NotImplementedException;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.CI_Obj;

import javax.ejb.Singleton;


/**
 * Created by Scorpio on 06.09.2017.
 */
@Singleton
public class CISrvcOGMImpl extends AbstractSrvcOGMImpl<CI> implements CISrvc {

    public String addKPIToCI(CI ci, KPI kpi) {
        throw new NotImplementedException("method String addKPIToCI(CI ci, KPI kpi) in CISrvcOGMImpl IS NOT IMPLEMRNTED YET");
    }


    public String addHIToCI(CI ci, HI hi) {
        throw new NotImplementedException("method String String addHIToCI(CI ci, HI hi)in CISrvcOGMImpl IS NOT IMPLEMRNTED YET");
    }


    @Override
    public Class getEntityType() {
        return CI_Obj.class;
    }

    public String linkCiToCi(CI startCi, CI endCi) {
        return null;
    }
}
