package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvStaticExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.PointExternal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Custom deserialiser for static CCTV JSON data.
 */
public class StaticCctvDeserialiser extends TrafficDataDeserializer<CctvStaticExternal>{

    public StaticCctvDeserialiser() {
        super(CctvStaticExternal.class);
    }

    /**
     * The received CCTV data contains nesting that is not necessary for the internal
     * data model. This deserialiser takes in this nested format and returns an
     * object with a flat structure that is easier to work with.
     * @param p Parser used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *   this deserialization activity.
     *
     * @return An object representing the static CCTV data.
     */
    @Override
    public CctvStaticExternal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = setConfiguredMapper(p);

        JsonNode root = mapper.readTree(p);
        String codeNumber = root.get("systemCodeNumber").asText();

        JsonNode definitionsContents = getNodeFromPointer(root, "/definitions/0");

        String shortDescription = definitionsContents.get("shortDescription").asText();
        String longDescription = definitionsContents.get("longDescription").asText();
        PointExternal point = getAsClass(definitionsContents.get("point"), PointExternal.class, mapper);
        ZonedDateTime lastUpdated = getAsClass(definitionsContents.get("lastUpdated"), ZonedDateTime.class, mapper);

        return CctvStaticExternal.builder()
                                 .systemCodeNumber(codeNumber)
                                 .shortDescription(shortDescription)
                                 .longDescription(longDescription)
                                 .point(point)
                                 .lastUpdated(lastUpdated)
                                 .build();
    }
}
