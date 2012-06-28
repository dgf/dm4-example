package de.deepamehta.plugins.example;

import de.deepamehta.core.service.PluginService;

public interface ExampleService extends PluginService {

    Example increase(long id);

}
