package de.deepamehta.plugins.example.model;

import static de.deepamehta.plugins.example.model.ExampleTopic.*;

import org.codehaus.jettison.json.JSONObject;

import de.deepamehta.core.DeepaMehtaTransaction;
import de.deepamehta.core.JSONEnabled;
import de.deepamehta.core.Topic;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.DeepaMehtaService;

/**
 * A domain model class that wraps the underlying <code>Topic</code>.
 */
public class Example implements JSONEnabled {

    private final Topic topic;
    private final DeepaMehtaService dms;

    /**
     * Loads an existing <code>Example</code> topic.
     */
    public Example(long id, DeepaMehtaService dms, ClientState clientState) {
        topic = dms.getTopic(id, true, clientState);
        this.dms = dms;
    }

    /**
     * Creates a new <code>Example</code> topic from <code>ExampleTopic</code> model.
     */
    public Example(ExampleTopic model, DeepaMehtaService dms, ClientState clientState) {
        topic = dms.createTopic(model, clientState);
        this.dms = dms;
    }

    /**
     * Increase the count by one.
     * 
     * @return this
     */
    public Example increase() {
        DeepaMehtaTransaction tx = dms.beginTx();
        topic.setChildTopicValue(COUNT, new SimpleValue(getCount() + 1));
        tx.success();
        tx.finish();
        return this;
    }

    @Override
    public JSONObject toJSON() {
        return topic.toJSON();
    }

    // ------------------------------ simplified composite access

    public int getCount() {
        return getCountTopic().getSimpleValue().intValue();
    }

    public String getName() {
        return getNameTopic().getSimpleValue().toString();
    }

    // ------------------------------ private helper

    private TopicModel getCountTopic() {
        return topic.getCompositeValue().getTopic(COUNT);
    }

    private TopicModel getNameTopic() {
        return topic.getCompositeValue().getTopic(NAME);
    }

}
