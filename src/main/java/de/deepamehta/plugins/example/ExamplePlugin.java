package de.deepamehta.plugins.example;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.Plugin;
import de.deepamehta.plugins.example.model.Example;
import de.deepamehta.plugins.example.model.ExampleTopic;
import de.deepamehta.plugins.example.service.ExampleService;

@Path("/example")
@Produces("application/json")
public class ExamplePlugin extends Plugin implements ExampleService {

    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * Initially change the count value of the unattached
     * <code>TopicModel</code> to zero.
     */
    @Override
    public void preCreateHook(TopicModel model, ClientState clientState) {
        if (model.getTypeUri().equals(ExampleTopic.TYPE)) {
            log.info("init Example count of "
                    + model.getCompositeValue().getString(ExampleTopic.NAME));
            model.getCompositeValue().put(ExampleTopic.COUNT, 0);
        }
    }

    /**
     * Creates a new <code>Example</code> instance based on
     * <code>ExampleTopic</code> model.
     */
    @POST
    @Path("/create")
    @Override
    public Example create(ExampleTopic topic, @HeaderParam("Cookie") ClientState clientState) {
        log.info("create Example " + topic);
        try {
            return new Example(topic, dms, clientState);
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        }
    }

    /**
     * Increase the count of an attached <code>Example</code> topic.
     */
    @GET
    @Path("/increase/{id}")
    @Override
    public Example increase(@PathParam("id") long id, @HeaderParam("Cookie") ClientState clientState) {
        log.info("increase Example " + id);
        try {
            return new Example(id, dms, clientState).increase();
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        }
    }
}
