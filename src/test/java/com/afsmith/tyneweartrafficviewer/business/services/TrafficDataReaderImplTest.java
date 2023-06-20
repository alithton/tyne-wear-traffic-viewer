package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.PlannedDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
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
        var incidents = reader.read(input, TrafficIncidentDTO.class);

        assertThat(incidents.size()).isGreaterThan(0);

        TrafficIncidentDTO testIncident = (TrafficIncidentDTO) incidents.get(0);

        assertThat(testIncident.point().latitude()).isGreaterThan(0);
        assertThat(testIncident.creationDate().getYear()).isEqualTo(2020);
        assertThat(testIncident.creationDate().getZone()).isEqualTo(ZoneId.of("+01:00"));
        assertThat(testIncident.type()).isEqualTo(TrafficDataTypes.INCIDENT);
    }

    @Test
    void readEventDataFromFile() throws IOException {
        var events = reader.read("events-test.json", TrafficEventDTO.class);

        assertThat(events.size()).isGreaterThan(0);

        TrafficEventDTO testEvent = (TrafficEventDTO) events.get(0);

        assertThat(testEvent.systemCodeNumber()).isEqualTo("Tyneandwear0001152597");
        assertThat(testEvent.creationDate().getYear()).isEqualTo(2023);
        assertThat(testEvent.planned()).isInstanceOf(PlannedDTO.class);
        assertThat(testEvent.planned().startTime().getMonth()).isEqualTo(Month.JUNE);
        assertThat(testEvent.type()).isEqualTo(TrafficDataTypes.EVENT);
    }
 }