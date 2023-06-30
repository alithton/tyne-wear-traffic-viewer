package com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeStaticExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StaticJourneyTimeDeserialiserTest {

    @Test
    void readStaticJourneytimeData() throws JsonProcessingException {
        String journeytimeJson = """
                {
                    "systemCodeNumber": "Vivacity_jtlink_9_6",
                    "definitions": [
                      {
                        "shortDescription": "A183 WB A1018 - Keel Sq",
                        "longDescription": "A183 St. Mary's Boulevard (Westbound) - A1018 West Wear Street to Keel Square",
                        "point": {
                          "easting": 439741,
                          "northing": 557269,
                          "latitude": 54.9085624674847000,
                          "longitude": -1.3816958272062500
                        },
                        "endPoint": {
                          "easting": 439417,
                          "northing": 557181,
                          "latitude": 54.9077924808644000,
                          "longitude": -1.3867551641951800
                        },
                        "lastUpdated": "2023-06-19T09:49:00.000+0100"
                      }
                    ]
                  }
                """;
        ObjectMapper mapper = new ObjectMapper();
        JourneytimeStaticExternal journeytime = mapper.readValue(journeytimeJson, JourneytimeStaticExternal.class);
        System.out.println("Parsed object: " + journeytime);

        assertThat(journeytime.getSystemCodeNumber()).isEqualTo("Vivacity_jtlink_9_6");
        assertThat(journeytime.getPoint().latitude()).isGreaterThan(54.9);
        assertThat(journeytime.getEndPoint().easting()).isEqualTo(439417);
        assertThat(journeytime.getLastUpdated().getYear()).isEqualTo(2023);
    }
}