package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.CctvDynamicExternal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;

/**
 * Deserialise dynamic CCTV data from the JSON format provided by the external
 * API. The external data contains a degree of nesting that is not needed by the
 * internal data model. This custom deserialiser removes that nested structure
 * and returns a 'flattened' data structure that is easier to work with.
 */
public class DynamicCctvDeserialiser extends TrafficDataDeserializer<CctvDynamicExternal>{

    public DynamicCctvDeserialiser() {
        super(CctvDynamicExternal.class);
    }

    @Override
    public CctvDynamicExternal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = setConfiguredMapper(p);

        JsonNode root = mapper.readTree(p);
        String codeNumber = root.get("systemCodeNumber").asText();

        JsonNode dynamicsContent = getNodeFromPointer(root, "/dynamics/0");
        String image = dynamicsContent.get("image").asText();
        URL imageUrl = new URL(image);
        ZonedDateTime lastUpdated = getAsClass(dynamicsContent.get("lastUpdated"), ZonedDateTime.class, mapper);

        return CctvDynamicExternal.builder()
                                  .systemCodeNumber(codeNumber)
                                  .image(imageUrl)
                                  .lastUpdated(lastUpdated)
                                  .build();
    }
}
