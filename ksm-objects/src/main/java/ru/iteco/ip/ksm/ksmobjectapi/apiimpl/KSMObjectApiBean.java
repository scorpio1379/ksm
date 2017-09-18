package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.*;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.HI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.KPI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.HI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.KPI_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedobjs.Service_Obj;
import ru.iteco.ip.ksm.ksmobjectapi.domain.noninheritedservices.CISrvc;
import ru.iteco.ip.ksm.logger.annotations.DefaultKSMLogger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Set;

/**
 * Created by administrator on 29.06.2017.
 */
@Stateless(name = "KSMObjectApiEJB" , mappedName = "KSMObjectApi")
@Remote(IKSMObjectApiRemote.class)
@Local(IKSMObjectApiLocal.class)
public class KSMObjectApiBean implements IKSMObjectApiRemote , IKSMObjectApiLocal {
    @Inject @DefaultKSMLogger
    private Logger logger;


    @Inject
    private CISrvc ciSrvc;
    @Inject
    private CIBuilder ciBuilder;

    @Inject
    private KPIBuilderFactory kpiBuilderFactory;

    @Inject
    private HIBuilderFactory hiBuilderFactory;

    @Inject
    private ServiceBuilderFactory serviceBuilderFactory;

    @Inject
    private CIToCILinker ci2CILinker;



    public KSMObjectApiBean() {

    }

    @PostConstruct
    private void initKSMObjectApiBean(){
        logger.debug("debug");
        logger.debug("KSMObjectApiBean inited");
    }

    @Override
    public void test()  {
        CI a = getCIBuilder().name("someName").description("description").statusKPIksmObjId("somestatusKpi").ksmObjId("00eb9a4e-08e3-4521-b3b4-6f70a562cc6e").build();

        KPI kpi = getKPIBuilder(a).name("kpiname").description("kpidescr").kpiType("typed").calcRuleId("ruleId").ksmObjId("11eb9a4e-08e3-4521-b3b4-6f70a562cc6e").build();

        HI hi = getHIBuilder(a).name("hiname").description("hidescr").ksmObjId("22eb9a4e-08e3-4521-b3b4-6f70a562cc6e").hiType("sometyped").build();

    }

    @Override
    public Set<Service> getAllKSMServices() {
        return Collections.emptySet();
    }

    @Override
    public Service getKSMServiceByKSMObjId(String ksmObjId) {
        return new Service_Obj();

    }

    @Override
    public CI getKSMCIByKSMObjId(String ksmObjId) {
        return ciSrvc.findByKsmObjId(ksmObjId);
    }

    @Override
    public KPI getKSMKPIByKSMObjId(String ksmObjId) {
        return new KPI_Obj();
    }

    @Override
    public HI getKSMHIByKSMObjId(String ksmObjId) {
        return new HI_Obj();
    }




    @Override
    public String deleteKSMObjectByKsmID(String ksmObjId) {
        throw new org.apache.commons.lang3.NotImplementedException("method deleteKSMObjectByKsmID(String ksmObjId) in  class KSMObjectApiBean NOT implemented YET");
    }

    @Override
    public String linkCIToCI(String startCIiD, String endCiId , String CI2CIRelationShipType ) {

        //ksmRelFab.createCIToCILink( startCIiD, endCiId , CI2CIRelationShipType );
        return "1";

    }

    @Override
    public CIBuilder getCIBuilder() {
        return this.ciBuilder;
    }

    @Override
    public KPIBuilder getKPIBuilder( CI ci)  {
        return this.kpiBuilderFactory.getKPIBuilder(ci);
    }

    @Override
    public KPIBuilder getKPIBuilder(String kpiKsmObjId) {
        logger.error("ERROR: METHOD getKPIBuilder(String kpiKsmObjId) in  KSMObjectApiBean NOT IMPLEMENTED YET");
        throw new NotImplementedException();
    }

    @Override
    public HIBuilder getHIBuilder(CI ci) {
        return this.hiBuilderFactory.getHIBuilder(ci);
    }
    @Override
    public ServiceBuilder getServiceBuilder(){
        return this.serviceBuilderFactory.getServiceBuilder();
    }


}