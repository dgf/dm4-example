package de.deepamehta.plugins.example.service;

import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.PluginService;
import de.deepamehta.plugins.example.model.Example;
import de.deepamehta.plugins.example.model.ExampleTopic;

public interface ExampleService extends PluginService {

    Example create(ExampleTopic topic, ClientState clientState);

    Example increase(long id, ClientState clientState);

}
