package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeStaticExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.PointExternal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.ZonedDateTime;

public class StaticJourneyTimeDeserialiser extends TrafficDataDeserializer<JourneytimeStaticExternal> {

    public StaticJourneyTimeDeserialiser() {
        super(JourneytimeStaticExternal.class);
    }

    @Override
    public JourneytimeStaticExternal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = setConfiguredMapper(p);

        JsonNode root = mapper.readTree(p);
        String systemCodeNumber = root.get("systemCodeNumber").asText();

        JsonNode definitionsContents = getNodeFromPointer(root, "/definitions/0");

        String shortDesc = definitionsContents.get("shortDescription").asText();
        String longDesc = definitionsContents.get("longDescription").asText();

        PointExternal point = getAsClass(definitionsContents.get("point"), PointExternal.class, mapper);
        PointExternal endPoint = getAsClass(definitionsContents.get("endPoint"), PointExternal.class, mapper);
        ZonedDateTime lastUpdated = getAsClass(definitionsContents.get("lastUpdated"), ZonedDateTime.class, mapper);

        return JourneytimeStaticExternal.builder()
                                   .systemCodeNumber(systemCodeNumber)
                                   .shortDescription(shortDesc)
                                   .longDescription(longDesc)
                                   .point(point)
                                   .endPoint(endPoint)
                                   .lastUpdated(lastUpdated)
                                   .build();
    }
}
