package ru.iteco.ip.ksm.web.rest;

import ru.iteco.ip.ksm.ksmobjects.KSMObjectApiBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Scorpio on 21.06.2017.
 */
@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class KSMRestServiceImpl {

    @EJB(beanName = "KSMObjectApiEJB")
    private KSMObjectApiBean ksmObjApi;


    public KSMRestServiceImpl() {
    }

    @GET
    @Path("/getModels")
    @Produces("application/json")
    public String getServiceModels(){
        return "someThing";
    }
}
