package ru.iteco.ip.ksm.web.rest;

import ru.iteco.ip.ksm.ksmobjectapi.api.IKSMObjectApiRemote;
import ru.iteco.ip.ksm.web.rest.models.KSMServiceModelImpl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

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
        /*
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executor.submit(new Runnable(){
            public void run() {
                ksmObjectApi.test();
            }
            });
        }*/

        try {
            ksmObjectApi.test();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "someThing";
    }
    @POST
    @Path("/loadServiceFromXML")
    @Consumes({"application/xml", "text/xml", "application/json"})
    public String loadServiceFromXML(KSMServiceModelImpl ksmServiceModel){
        /**
        CI ci = ksmObjectApi.getCIBuilder().description("").ksmCiType(KSMCIType.REGULAR).name("somename").build();
        String serviceCIId = ksmObjectApi.createCI(ksmServiceModel.tmpId, ksmServiceModel.name, "SERVICE");
        for(ServiceKPI serviceKpi:ksmServiceModel.kpis){
            ksmObjectApi.createKPI(serviceCIId,serviceKpi.tmpId , serviceKpi.name ,serviceKpi.kpiType);
            ksmObjectApi.getCIBuilder().build().
        }
        for(ServiceCIImpl serviceCi:ksmServiceModel.serviceCIList){
            String ciKsmObjId = ksmObjectApi.createCI(serviceCi.tmpId, serviceCi.name, serviceCi.serviceType);
            for ( ServiceHI hi :serviceCi.his){
                ksmObjectApi.createHI(ciKsmObjId,hi.tmpId,hi.name,hi.hiType);
            }
            for (ServiceKPI skpi:serviceCi.kpis){
                ksmObjectApi.createKPI(ciKsmObjId,skpi.tmpId,skpi.name,skpi.kpiType);
            }
        }
        for(ServiceModelRelationShipImpl relation : ksmServiceModel.serviceModelRelationShips){
            ksmObjectApi.linkCIToCI(relation.startNodeId , relation.endNodeId , relation.relationType);
        }
        //serviceKPIid =
        return "youre string is" + ksmServiceModel;
         */
        return  "NOT IMPLEMENTED";
    }
}
