package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneytimeStaticDTO;
import com.afsmith.tyneweartrafficviewer.business.data.PointDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.ZonedDateTime;

public class StaticJourneyTimeDeserialiser extends TrafficDataDeserializer<JourneytimeStaticDTO> {

    public StaticJourneyTimeDeserialiser() {
        super(JourneytimeStaticDTO.class);
    }

    @Override
    public JourneytimeStaticDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = setConfiguredMapper(p);

        JsonNode root = mapper.readTree(p);
        String systemCodeNumber = root.get("systemCodeNumber").asText();

        JsonNode definitionsContents = getNodeFromPointer(root, "/definitions/0");

        String shortDesc = definitionsContents.get("shortDescription").asText();
        String longDesc = definitionsContents.get("longDescription").asText();

        PointDTO point = getAsClass(definitionsContents.get("point"), PointDTO.class, mapper);
        PointDTO endPoint = getAsClass(definitionsContents.get("endPoint"), PointDTO.class, mapper);
        ZonedDateTime lastUpdated = getAsClass(definitionsContents.get("lastUpdated"), ZonedDateTime.class, mapper);

        return JourneytimeStaticDTO.builder()
                .systemCodeNumber(systemCodeNumber)
                .shortDescription(shortDesc)
                .longDescription(longDesc)
                .point(point)
                .endPoint(endPoint)
                .lastUpdated(lastUpdated)
                                   .build();
    }
}
