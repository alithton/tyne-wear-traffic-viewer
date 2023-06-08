package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficDataReaderImplTest {

    TrafficDataReader reader = new TrafficDataReaderImpl();

    @Test
    void read() throws IOException {
        Path inputPath = Paths.get(System.getProperty("user.dir"), "src/test/resources/data/incidents-test.json");
        InputStream input = Files.newInputStream(inputPath);
        var incidents = reader.read(input);

        assertThat(incidents.size()).isGreaterThan(0);

        TrafficIncidentDTO testIncident = incidents.get(0);

        assertThat(testIncident.point().latitude()).isGreaterThan(0);
        assertThat(testIncident.creationDate().getYear()).isEqualTo(2020);
        assertThat(testIncident.creationDate().getZone()).isEqualTo(ZoneId.of("+01:00"));
    }
 }