package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeDynamicExternal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Custom JSON deserialiser for dynamic journey time data.
 */
public class DynamicJourneyTimeDeserialiser extends TrafficDataDeserializer<JourneytimeDynamicExternal>{

    public DynamicJourneyTimeDeserialiser() {
        super(JourneytimeDynamicExternal.class);
    }
    @Override
    public JourneytimeDynamicExternal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = setConfiguredMapper(p);

        JsonNode root = mapper.readTree(p);
        String systemCodeNumber = root.get("systemCodeNumber").asText();

        JsonNode dynamicsContent = getNodeFromPointer(root, "/dynamics/0");
        int linkTravelTime = getIntegerOrZero(dynamicsContent.get("linkTravelTime"));
        int platesIn = getIntegerOrZero(dynamicsContent.get("platesIn"));
        int platesOut = getIntegerOrZero(dynamicsContent.get("platesOut"));
        int plateMatches = getIntegerOrZero(dynamicsContent.get("plateMatches"));
        ZonedDateTime lastUpdate = getAsClass(dynamicsContent.get("lastUpdated"), ZonedDateTime.class, mapper);

        return JourneytimeDynamicExternal.builder()
                                         .systemCodeNumber(systemCodeNumber)
                                         .linkTravelTime(linkTravelTime)
                                         .platesIn(platesIn)
                                         .platesOut(platesOut)
                                         .plateMatches(plateMatches)
                                         .lastUpdated(lastUpdate)
                                         .build();
    }

    private int getIntegerOrZero(JsonNode node) {
        int result;
        try {
            result = Integer.parseInt(node.asText());
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }
}
