package de.deepamehta.plugins.example;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.Plugin;

@Path("/example")
@Produces("application/json")
public class ExamplePlugin extends Plugin implements ExampleService {

    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public void preCreateHook(TopicModel model, ClientState clientState) {
        log.info("init example count of " + model.getId());
        try {
            model.getCompositeValue().put("dm4.example.count", 0);
        } catch (Exception e) {
            log.warning("something went wrong");
        }
    }

    @GET
    @Path("/increase/{id}")
    @Override
    public Example increase(@PathParam("id") long id) {
        log.info("increase " + id);
        try {
            return new Example(id, dms).increase();
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        }
    }

}
