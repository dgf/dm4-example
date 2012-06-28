package de.deepamehta.plugins.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jettison.json.JSONObject;

// TODO move it to core or web service module and rewrite existing provider
public abstract class AbstractProvider<Type> implements MessageBodyWriter<Type> {

    private final Class<Type> typeClass;

    protected abstract JSONObject getJsonData(Type t);

    protected AbstractProvider(Class<Type> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public boolean isWriteable(Class<?> type, java.lang.reflect.Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type == typeClass && mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(Type t, Class<?> type, java.lang.reflect.Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Type t, Class<?> type, java.lang.reflect.Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(entityStream));
            getJsonData(t).write(writer);
            writer.flush();
        } catch (Exception e) {
            throw new IOException("Writing message body failed (" + t + ")", e);
        }
    }

}