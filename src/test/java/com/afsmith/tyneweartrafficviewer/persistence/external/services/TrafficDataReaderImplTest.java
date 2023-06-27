package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficEventExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficIncidentExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficDataReaderImplTest {

    TrafficDataReader reader = TrafficDataReaderImpl.fromFilePath("src/test/resources/data");

    @Test
    void readIncidentDataFromFile() throws IOException {
        Path inputPath = Paths.get(System.getProperty("user.dir"), "src/test/resources/data/incidents-test.json");
        InputStream input = Files.newInputStream(inputPath);
        var incidents = reader.read(input, TrafficIncidentExternal.class);

        assertThat(incidents.size()).isGreaterThan(0);

        TrafficIncidentExternal testIncident = (TrafficIncidentExternal) incidents.get(0);

        assertThat(testIncident.getPoint().latitude()).isGreaterThan(0);
        assertThat(testIncident.getCreationDate().getYear()).isEqualTo(2020);
        assertThat(testIncident.getCreationDate().getZone()).isEqualTo(ZoneId.of("+01:00"));
        assertThat(testIncident.getType()).isEqualTo(TrafficDataTypes.INCIDENT);
    }

    @Test
    void readEventDataFromFile() throws IOException {
        var events = reader.read("events-test.json", TrafficEventExternal.class);

        assertThat(events.size()).isGreaterThan(0);

        TrafficEventExternal testEvent = (TrafficEventExternal) events.get(0);

        assertThat(testEvent.getSystemCodeNumber()).isEqualTo("Tyneandwear0001152597");
        assertThat(testEvent.getCreationDate().getYear()).isEqualTo(2023);
        assertThat(testEvent.getPlanned()).isInstanceOf(PlannedDTO.class);
        assertThat(testEvent.getPlanned().startTime().getMonth()).isEqualTo(Month.JUNE);
        assertThat(testEvent.getType()).isEqualTo(TrafficDataTypes.EVENT);
    }

    @Test
    void readStaticJourneytimeData() throws JsonProcessingException, JsonMappingException {
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
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(JourneytimeStaticDTO.class, new StaticJourneyTimeDeserialiser());
//        mapper.registerModule(module);
        JourneytimeStaticDTO journeytime = mapper.readValue(journeytimeJson, JourneytimeStaticDTO.class);
        System.out.println("Parsed object: " + journeytime);

        assertThat(journeytime.getSystemCodeNumber()).isEqualTo("Vivacity_jtlink_9_6");
        assertThat(journeytime.getPoint().latitude()).isGreaterThan(54.9);
        assertThat(journeytime.getEndPoint().easting()).isEqualTo(439417);
        assertThat(journeytime.getLastUpdated().getYear()).isEqualTo(2023);
    }
 }