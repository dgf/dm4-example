package de.deepamehta.plugins.example.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.deepamehta.core.model.CompositeValue;
import de.deepamehta.core.model.TopicModel;

public class ExampleTopic extends TopicModel {

    public static final String COUNT = "dm4.example.count";
    public static final String NAME = "dm4.example.name";
    public static final String TYPE = "dm4.example.type";

    /**
     * @param model { name: "an example" }
     * @throws JSONException
     */
    public ExampleTopic(JSONObject json) throws JSONException {
        super(TYPE);
        setCompositeValue(new CompositeValue().put(NAME, json.getString("name")));
    }

}
