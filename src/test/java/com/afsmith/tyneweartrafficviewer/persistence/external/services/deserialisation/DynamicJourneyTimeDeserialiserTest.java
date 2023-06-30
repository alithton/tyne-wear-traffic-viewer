package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeDynamicExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicJourneyTimeDeserialiserTest {

    @Test
    public void readDynamicJourneytimeData() throws JsonProcessingException {
        String dynamicJourneytimeJson = """
                {
                    "systemCodeNumber": "CAJT_GHA167_DR2A_DR2",
                    "dynamics": [
                      {
                        "linkTravelTime": 85,
                        "platesIn": 0,
                        "platesOut": 16,
                        "plateMatches": 0,
                        "lastUpdated": "2023-06-23T17:18:00.000+0100"
                      }
                    ]
                  }
                  """;
        ObjectMapper mapper = new ObjectMapper();
        JourneytimeDynamicExternal journeytime = mapper.readValue(dynamicJourneytimeJson, JourneytimeDynamicExternal.class);

        assertThat(journeytime.getSystemCodeNumber()).isEqualTo("CAJT_GHA167_DR2A_DR2");
        assertThat(journeytime.getLinkTravelTime()).isEqualTo(85);
        assertThat(journeytime.getPlatesIn()).isEqualTo(0);
        assertThat(journeytime.getPlatesOut()).isEqualTo(16);
        assertThat(journeytime.getPlateMatches()).isEqualTo(0);
        assertThat(journeytime.getLastUpdated().getDayOfMonth()).isEqualTo(23);
    }

    @Test
    public void readDynamicJourneyTimesDataWithNulls() throws JsonProcessingException {
        String json = """
                {
                    "systemCodeNumber": "R9800WTYQ025Y",
                    "dynamics": [
                      {
                        "linkTravelTime": 215,
                        "platesIn": null,
                        "platesOut": null,
                        "plateMatches": null,
                        "lastUpdated": "2023-06-23T17:14:00.000+0100"
                      }
                    ]
                  }
                """;

        ObjectMapper mapper = new ObjectMapper();
        JourneytimeDynamicExternal journeytime = mapper.readValue(json, JourneytimeDynamicExternal.class);

        assertThat(journeytime.getSystemCodeNumber()).isEqualTo("R9800WTYQ025Y");
        assertThat(journeytime.getLinkTravelTime()).isEqualTo(215);
        assertThat(journeytime.getPlatesIn()).isEqualTo(0);
        assertThat(journeytime.getPlatesOut()).isEqualTo(0);
        assertThat(journeytime.getPlateMatches()).isEqualTo(0);
        assertThat(journeytime.getLastUpdated().getDayOfMonth()).isEqualTo(23);
    }
}