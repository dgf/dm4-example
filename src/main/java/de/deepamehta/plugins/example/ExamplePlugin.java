package de.deepamehta.plugins.example;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import de.deepamehta.core.service.Plugin;

@Path("/example")
@Produces("application/json")
public class ExamplePlugin extends Plugin implements ExampleService {

    private Logger log = Logger.getLogger(getClass().getName());

    @GET
    @Path("/increase/{id}")
    @Override
    public Example increase(@PathParam("id") long id) {
        log.info("increase " + id);
        return new Example(id, dms).increase();
    }


}
