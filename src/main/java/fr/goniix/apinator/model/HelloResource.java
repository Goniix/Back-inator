package fr.goniix.apinator.model;

import fr.goniix.apinator.utils.DS;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        DS managerTest = DS.getInstance();
        return "Hello, World!";
    }

}