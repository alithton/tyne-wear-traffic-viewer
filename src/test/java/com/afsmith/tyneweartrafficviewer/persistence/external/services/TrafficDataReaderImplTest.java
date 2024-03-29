package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.*;
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

        TrafficIncidentExternal testIncident = incidents.get(0);

        assertThat(testIncident.getPoint().latitude()).isGreaterThan(0);
        assertThat(testIncident.getCreationDate().getYear()).isEqualTo(2020);
        assertThat(testIncident.getCreationDate().getZone()).isEqualTo(ZoneId.of("+01:00"));
        assertThat(testIncident.getType()).isEqualTo(TrafficDataTypes.INCIDENT);
    }

    @Test
    void readEventDataFromFile() throws IOException {
        var events = reader.read("events-test.json", TrafficEventExternal.class);

        assertThat(events.size()).isGreaterThan(0);

        TrafficEventExternal testEvent = events.get(0);

        assertThat(testEvent.getSystemCodeNumber()).isEqualTo("Tyneandwear0001152597");
        assertThat(testEvent.getCreationDate().getYear()).isEqualTo(2023);
        assertThat(testEvent.getPlanned()).isInstanceOf(PlannedExternal.class);
        assertThat(testEvent.getPlanned().startTime().getMonth()).isEqualTo(Month.JUNE);
        assertThat(testEvent.getType()).isEqualTo(TrafficDataTypes.EVENT);
    }

    @Test
    void readDynamicJourneytimeDataFromFile() throws IOException {
        var journeytimeDynamic = reader.read("journeytime-dynamic-full-test.json", JourneytimeDynamicExternal.class);

        assertThat(journeytimeDynamic.size()).isGreaterThan(0);
    }

//    @Test
//    void createCollectionClass() {
//        var mapper = JsonMapper.builder()
//                               .findAndAddModules()
//                               .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
//                               .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
//                               .build()
//                               .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        var incidentList = mapper.getTypeFactory()
//                                 .constructCollectionType(List.class, JourneytimeDynamicExternal.class);
//
//        System.out.println(incidentList);
//
//        assertThat(incidentList).isNotNull();
//    }
 }