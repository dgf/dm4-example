package de.deepamehta.plugins.example.service;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.PluginService;
import de.deepamehta.plugins.example.model.ExampleTopic;

public interface ExampleService extends PluginService {

    Topic create(ExampleTopic topic, ClientState clientState);

    Topic increase(long id, ClientState clientState);

}
