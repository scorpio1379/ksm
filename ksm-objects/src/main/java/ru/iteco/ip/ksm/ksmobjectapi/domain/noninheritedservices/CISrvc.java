package ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices;


import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;

/**
 * Created by Scorpio on 06.09.2017.
 */
public interface CISrvc extends AbstractSrvc<CI> {
    String addKPIToCI(CI ci, KPI kpi);
    String addHIToCI(CI ci, HI hi);
    String linkCiToCi(CI startCi , CI endCi);
}
