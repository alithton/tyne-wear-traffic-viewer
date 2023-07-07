package com.afsmith.tyneweartrafficviewer.persistence.routing.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPoint;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GeoJsonPointDeserialiser extends StdDeserializer<GeoJsonPoint> {

    public GeoJsonPointDeserialiser() {
        super(GeoJsonPoint.class);
    }

    @Override
    public GeoJsonPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        String json = mapper.readTree(p).toString();

        double[] longLat = mapper.readValue(json, double[].class);
        return new GeoJsonPoint(longLat[1], longLat[0]);
    }
}
