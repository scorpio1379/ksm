package ru.iteco.ip.ksm.web.rest;

import ru.iteco.ip.ksm.ksmobjectapi.api.IKSMObjectApiRemote;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.CI;
import ru.iteco.ip.ksm.ksmobjectapi.domain.ksmobjects.Service;
import ru.iteco.ip.ksm.ksmobjectapi.domain.objects.cis.KSMCIType;
import ru.iteco.ip.ksm.web.rest.models.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Scorpio on 21.06.2017.
 */
@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class KSMRestServiceImpl {

    @EJB
    //@Inject @Default
    private IKSMObjectApiRemote ksmObjectApi;


    public KSMRestServiceImpl() {
    }

    @GET
    @Path("/getModels")
    @Produces("application/json")
    public String getServiceModels(){
        ksmObjectApi.test();

        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executor.submit(() -> ksmObjectApi.test());
        }

        return "someThing";
    }
    @POST
    @Path("/loadServiceFromXML")
    @Consumes({"application/xml", "text/xml", "application/json"})
    public String loadServiceFromXML(KSMServiceModelImpl ksmServiceModel){
        Service srvc = ksmObjectApi.getServiceBuilder().ksmObjId(ksmServiceModel.tmpId)
                .description(ksmServiceModel.description)
                .name(ksmServiceModel.name)
                .statusKPIksmObjId(ksmServiceModel.status_kpi_id)
                .ksmCiType(KSMCIType.SERVICE)
                .build();

        if (ksmServiceModel.kpis!=null){
            for(ServiceKPI serviceKpi:ksmServiceModel.kpis){
                ksmObjectApi.getKPIBuilder(srvc)
                        .ksmObjId(serviceKpi.tmpId)
                        .calcRuleId(serviceKpi.ruleFileName)
                        .kpiType(serviceKpi.kpiType)
                        .description(serviceKpi.displayName)
                        .name(serviceKpi.name)
                        .build();
            }
        }
        if (ksmServiceModel.his!=null){
            for(ServiceHI serviceHI:ksmServiceModel.his){
                ksmObjectApi.getHIBuilder(srvc)
                        .hiType(serviceHI.hiType)
                        .description(serviceHI.displayName)
                        .ksmObjId(serviceHI.tmpId)
                        .name(serviceHI.name)
                        .build();
            }

        }



        for(ServiceCIImpl serviceCi:ksmServiceModel.serviceCIList){
            CI ci = ksmObjectApi.getCIBuilder()
                    .ksmCiType(KSMCIType.REGULAR)
                    .name(serviceCi.name)
                    .description(serviceCi.description)
                    .statusKPIksmObjId(serviceCi.status_kpi_id)
                    .ksmObjId(serviceCi.tmpId)
                    .build();
            for ( ServiceHI hi :serviceCi.his){
                ksmObjectApi.getHIBuilder(ci)
                        .hiType(hi.hiType)
                        .description(hi.displayName)
                        .ksmObjId(hi.tmpId)
                        .name(hi.name)
                        .build();
            }
            for (ServiceKPI skpi:serviceCi.kpis){
                ksmObjectApi.getKPIBuilder(ci)
                        .ksmObjId(skpi.tmpId)
                        .calcRuleId(skpi.ruleFileName)
                        .kpiType(skpi.kpiType)
                        .description(skpi.displayName)
                        .name(skpi.name)
                        .build();
            }
        }
        for(ServiceModelRelationShipImpl relation : ksmServiceModel.serviceModelRelationShips){
            ksmObjectApi.linkCIToCI(relation.startNodeId , relation.endNodeId , relation.relationType);
        }
        return "youre string is" + ksmServiceModel;

    }
}
