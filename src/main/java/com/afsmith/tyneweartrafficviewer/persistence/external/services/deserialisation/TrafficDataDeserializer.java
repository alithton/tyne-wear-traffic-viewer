package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;

/**
 * Abstract class defining some convenience methods for deserializing JSON data
 * from the Open Data Service.
 * @param <T> The type of data to deserialize in to.
 */
public abstract class TrafficDataDeserializer<T> extends StdDeserializer<T> {

    protected TrafficDataDeserializer(Class<T> clazz) {
        super(clazz);
    }

    /**
     * Get a Jackson ObjectMapper that is correctly configured for reading date
     * values in the Open Data Service JSON data.
     * @param parser The parser for the current node.
     * @return A configured ObjectMapper.
     */
    protected ObjectMapper setConfiguredMapper(JsonParser parser) {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        return mapper.registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
    }

    /**
     * Get the JSON node that is pointed to by the provided JSON Pointer query
     * ({@see https://datatracker.ietf.org/doc/html/rfc6901}).
     * @param node A JSON node within which to search using the pointer.
     * @param pointerText The JSON pointer query.
     * @return The JSON node retrieved by the query.
     */
    protected JsonNode getNodeFromPointer(JsonNode node, String pointerText) {
        JsonPointer pointer = JsonPointer.compile(pointerText);
        return node.withObject(pointer);
    }

    /**
     * Convert the contents of a JSON node into a specified class, if possible,
     * otherwise will throw an exception.
     * @param node The JSON node to convert.
     * @param clazz The class to convert the node into.
     * @param mapper A Jackson ObjectMapper.
     * @return An object of the specified type.
     * @param <U> The type of the object to be returned.
     * @throws JsonProcessingException If the mapper is unable to convert the node
     * contents into an object of the specified type.
     */
    protected <U> U getAsClass(JsonNode node, Class<U> clazz, ObjectMapper mapper) throws JsonProcessingException {
        return mapper.readValue(node.toString(), clazz);
    }
}
