package de.deepamehta.plugins.example.provider;

import javax.ws.rs.ext.Provider;

import org.codehaus.jettison.json.JSONObject;

import de.deepamehta.plugins.example.AbstractProvider;
import de.deepamehta.plugins.example.Example;

@Provider
public class ExampleProvider extends AbstractProvider<Example> {

    public ExampleProvider() {
        super(Example.class);
    }

    @Override
    protected JSONObject getJsonData(Example example) {
        return example.toJSON();
    }

}
