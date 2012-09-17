package de.deepamehta.plugins.example;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.osgi.PluginActivator;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.event.PreCreateTopicListener;
import de.deepamehta.plugins.example.model.Example;
import de.deepamehta.plugins.example.model.ExampleTopic;
import de.deepamehta.plugins.example.service.ExampleService;

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
public class ExamplePlugin extends PluginActivator implements ExampleService,
        PreCreateTopicListener {

    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * Initially change the count value of the unattached
     * <code>TopicModel</code> to zero.
     */
    @Override
    public void preCreateTopic(TopicModel model, ClientState clientState) {
        if (model.getTypeUri().equals(ExampleTopic.COUNT)) {
            log.info("init Example count");
            model.setSimpleValue(0);
        }
    }

    /**
     * Creates a new <code>Example</code> instance based on the domain specific
     * REST call with a alternate JSON topic representation.
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
