package ru.iteco.ksm.web.rest;

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
@Path("/")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class KSMRestServiceImpl {
    @Inject
    private
    @GET
    @Path("/getModels")
    @Produces("application/json")
    public void getServiceModels(){

    }
}
