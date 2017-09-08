package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;


import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.CI_Obj;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * Created by Scorpio on 06.09.2017.
 */
@Default
@Stateless
public class CISrvcOGMImpl extends AbstractSrvcOGMImpl<CI> implements CISrvc {

    public String addKPIToCI(CI ci, KPI kpi) {
        return null;
    }


    public String addHIToCI(CI ci, HI hi) {
        return null;
    }


    @Override
    public Class getEntityType() {
        return CI_Obj.class;
    }

    public String linkCiToCi(CI startCi, CI endCi) {
        return null;
    }
}
