package de.deepamehta.plugins.example;

import org.codehaus.jettison.json.JSONObject;

import de.deepamehta.core.Topic;
import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.DeepaMehtaService;

public class Example {

    private Topic topic;
    private final DeepaMehtaService dms;

    public Example(long topicId, DeepaMehtaService dms) {
        this.dms = dms;
        topic = dms.getTopic(topicId, true, null);
    }

    public Example increase() {
        TopicModel count = getCountTopic();
        count.setSimpleValue(count.getSimpleValue().intValue() + 1);
        dms.updateTopic(count, null);
        return this;
    }

    // ------------------------------ simplified composite access

    public long getCount() {
        return getCountTopic().getSimpleValue().longValue();
    }

    public String getName() {
        return getNameTopic().getSimpleValue().toString();
    }

    // ------------------------------ topic delegates

    public JSONObject toJSON() {
        return topic.toJSON();
    }

    public long getId() {
        return topic.getId();
    }

    public String getUri() {
        return topic.getUri();
    }

    public String getTypeUri() {
        return topic.getTypeUri();
    }

    // ------------------------------ private helper

    private TopicModel getCountTopic() {
        return topic.getCompositeValue().getTopic("dm4.example.count");
    }

    private TopicModel getNameTopic() {
        return topic.getCompositeValue().getTopic("dm4.example.name");
    }

}
