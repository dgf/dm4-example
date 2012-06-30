package de.deepamehta.plugins.example.provider;

import java.io.InputStream;

import javax.ws.rs.ext.Provider;

import org.codehaus.jettison.json.JSONObject;

import de.deepamehta.core.util.JavaUtils;
import de.deepamehta.plugins.example.AbstractJaxrsReader;
import de.deepamehta.plugins.example.model.ExampleTopic;

/**
 * Creates a <code>ExampleTopic</code> from JSON.
 * 
 * @see ExampleTopic#ExampleTopic(JSONObject)
 */
@Provider
public class ExampleTopicProvider extends AbstractJaxrsReader<ExampleTopic> {

    public ExampleTopicProvider() {
        super(ExampleTopic.class);
    }

    @Override
    protected ExampleTopic createInstance(InputStream entityStream) throws Exception {
        return new ExampleTopic(new JSONObject(JavaUtils.readText(entityStream)));
    }

}
