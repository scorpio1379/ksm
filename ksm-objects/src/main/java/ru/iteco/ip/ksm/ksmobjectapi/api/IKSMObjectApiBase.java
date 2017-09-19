package ru.iteco.ip.ksm.ksmobjectapi.api;

import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;

import java.util.Set;

/**
 * Created by administrator on 04.07.2017.
 */
public interface IKSMObjectApiBase {


    void test() ;

    Set<Service> getAllKSMServices();
    /* ---- общие геттеры, поидее должны быть реализованы одной функцией getKSMObjByKSMId с последующим приведением типа*/
    Service getKSMServiceByKSMObjId(String ksmObjId);
    CI getKSMCIByKSMObjId(String ksmObjId);
    KPI getKSMKPIByKSMObjId(String ksmObjId);
    HI getKSMHIByKSMObjId(String ksmObjId);
    /*------ Общие сеттеры, отдельная реализация в каждом сервисе*/

    /* общая функция*/
    String deleteKSMObjectByKsmID(String ksmObjId);
    /* пока существует единственный тип связи */
    void linkCIToCI(String startCIiD, String endCiId, String CI2CIRelationShipType);

    CIBuilder getCIBuilder();
    CIBuilder getCIBuilder(String ciKsmObjId);

    KPIBuilder getKPIBuilder( CI ci) ;
    KPIBuilder getKPIBuilder(String kpiKsmObjId);

    HIBuilder getHIBuilder(CI ci);

    ServiceBuilder getServiceBuilder();


    

}
