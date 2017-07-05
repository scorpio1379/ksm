package ru.iteco.ip.ksm.web.rest;

import ru.iteco.ip.ksm.ksmobjects.api.IKSMObjectApiRemote;
import ru.iteco.ip.ksm.web.rest.models.KSMServiceModel;
import ru.iteco.ip.ksm.web.rest.models.KSMServiceModelImpl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        return "someThing";
    }
    @POST
    @Path("/loadServiceFromXML")
    @Consumes({"application/xml", "text/xml", "application/json"})
    public String loadServiceFromXML(KSMServiceModelImpl ksmServiceModel){
        return "youre string is" + ksmServiceModel;
    }
}
