package ru.iteco.ip.ksm.ksmobjectapi.apiimpl;

import org.slf4j.Logger;
import ru.iteco.ip.ksm.ksmobjectapi.api.CIBuilder;
import ru.iteco.ip.ksm.ksmobjectapi.api.IKSMObjectApiLocal;
import ru.iteco.ip.ksm.ksmobjectapi.api.IKSMObjectApiRemote;
import ru.iteco.ip.ksm.ksmobjectapi.api.KPIBuilder;
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



    public KSMObjectApiBean() {

    }

    @PostConstruct
    private void initKSMObjectApiBean(){
        logger.debug("debug");
        logger.debug("KSMObjectApiBean inited");
    }

    @Override
    public void test() throws NoSuchFieldException, IllegalAccessException {
        CI a = getCIBuilder().name("someName").description("description").statusKPIksmObjId("somestatusKpi").ksmObjId("00eb9a4e-08e3-4521-b3b4-6f70a562cc6e").build();

        KPI kpi = getKPIBuilder(a).name("kpiname").description("kpidescr").kpiType("typed").calcRuleId("ruleId").ksmObjId("11eb9a4e-08e3-4521-b3b4-6f70a562cc6e").build();


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







    /**--------------------------------------------------------------------------------------------------------------------------------
    public String createCI( String name )  {
        KSMCIType ksmciType = null;
        try {
            if (ksmciType==null) {
                logger.error("строковая переменная ciType {} не может быть преобразована в ненулевое значение enum KSMSCIType , будет использовано значение по умолчанию CIType.REGULAR", ciType);
                //throw new IllegalArgumentException("KSM CI Type not known");
                ksmciType = KSMCIType.REGULAR;
            }

        } catch (IllegalArgumentException | NullPointerException  e) {
            logger.error("строковая переменная ciType {} не может быть преобразована в enum KSMSCIType , будет использовано значение по умолчанию CIType.REGULAR , ошибка IllegalArgumentException {}", ciType , e.getStackTrace());
            ksmciType = KSMCIType.REGULAR;
        }
        KSMCI ci = ksmObjFab.createKSMCI(uuid, name, ksmciType);
        return ci.getKsmObjId();

    }


    public String createKPI(String ciKsmObjId, String ksmKpiObjId, String name, String kpiType) {
        KSMKPIType ksmKpiType;
        try{
            ksmKpiType = KSMKPIType.valueOf(kpiType);
            if(ksmKpiType == null){
                logger.error("строковая переменная kpiType {} не может быть преобразована в ненулевое значение enum KSMSCIType , будет использовано значение по умолчанию KSMKPIType.REGULAR", kpiType);
                ksmKpiType = KSMKPIType.REGULAR;
            }
        }catch (IllegalArgumentException | NullPointerException  e) {
            logger.error("строковая переменная kpiType {} не может быть преобразована в enum KSMSCIType , будет использовано значение по умолчанию KSMKPIType.REGULAR , ошибка IllegalArgumentException {}", kpiType , e.getStackTrace());
            ksmKpiType = KSMKPIType.REGULAR;
        }
        KSMCI ci = ksmObjFab.getKSMCIByKsmObjId(ciKsmObjId);
            /*TODO: правильно ли создавать KPI через CI или надо создавать метод в фабрике
        KSMKPI kpi = ksmObjFab.addKSMKPIToKSMCIByKsmObjId(ciKsmObjId, ksmKpiObjId, name, ksmKpiType);

        return kpi.getKsmObjId();
    }

    public String createHI(String ciKsmObjId, String ksmHiObjId, String name, String hiType) {
        KSMHIType ksmhiType;
        try{
            ksmhiType = KSMHIType.valueOf(hiType);
            if(ksmhiType== null){
                logger.error("строковая переменная hiType {} не может быть преобразована в ненулевое значение enum KSMHIType , будет использовано значение по умолчанию  KSMHIType.EVENT", hiType);
                ksmhiType = KSMHIType.EVENT;
            }
        }catch (IllegalArgumentException | NullPointerException  e) {
            logger.error("строковая переменная ksmhiType {} не может быть преобразована в enum KSMHIType , будет использовано значение по умолчанию  KSMHIType.EVENT , ошибка IllegalArgumentException {}", hiType , e.getStackTrace());
            ksmhiType = KSMHIType.EVENT;
        }
        KSMCI ci = ksmObjFab.getKSMCIByKsmObjId(ciKsmObjId);
            /*TODO: правильно ли создавать HI через CI или надо создавать метод в фабрике
        KSMHI hi = ksmObjFab.addKSMHIToKSMCIByKsmObjId(ciKsmObjId, ksmHiObjId, name, ksmhiType);

        return hi.getKsmObjId();
    }
    -----------------------------------------------------------------------------------------------------------------------------------*/

    @Override
    public String deleteKSMObjectByKsmID(String ksmObjId) {
        return "no";
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
    public KPIBuilder getKPIBuilder( CI ci) throws NoSuchFieldException, IllegalAccessException {
        return this.kpiBuilderFactory.getKPIBuilder(ci);
    }

    @Override
    public KPIBuilder getKPIBuilder(String kpiKsmObjId) {
        logger.error("ERROR: METHOD getKPIBuilder(String kpiKsmObjId) in  KSMObjectApiBean NOT IMPLEMENTED YET");
        throw new NotImplementedException();
    }


}
