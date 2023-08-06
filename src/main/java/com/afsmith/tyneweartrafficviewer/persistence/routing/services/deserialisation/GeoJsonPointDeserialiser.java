package com.afsmith.tyneweartrafficviewer.persistence.routing.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPointExternal;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * A custom Jackson deserialiser for reading in GeoJson-formatted point data.
 */
public class GeoJsonPointDeserialiser extends StdDeserializer<GeoJsonPointExternal> {

    /**
     * The default constructor for the custom deserialiser.
     */
    public GeoJsonPointDeserialiser() {
        super(GeoJsonPointExternal.class);
    }

    /**
     * Method for reading GeoJson-formatted coordinate data into a GeoJson point
     * object.
     * @param p Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *   this deserialization activity.
     *
     * @return A GeoJson point object.
     */
    @Override
    public GeoJsonPointExternal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        String json = mapper.readTree(p).toString();

        double[] longLat = mapper.readValue(json, double[].class);
        return new GeoJsonPointExternal(longLat[1], longLat[0]);
    }
}
