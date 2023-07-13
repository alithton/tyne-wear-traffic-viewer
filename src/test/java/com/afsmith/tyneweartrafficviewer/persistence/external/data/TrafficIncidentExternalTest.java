package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficIncidentExternalTest {
    TrafficIncidentExternal externalIncidents;

    @BeforeEach
    void setUp() {
        externalIncidents = buildIncident("code1");
    }

    @Test
    void convertExternalIncidentToEntity() {
        TrafficIncident incident = externalIncidents.toEntity();
        assertThat(incident.getSystemCodeNumber()).isEqualTo("code1");
    }

    private TrafficIncidentExternal buildIncident(String code) {
        return TrafficIncidentExternal.builder()
                .systemCodeNumber(code)
                                      .build();
    }
}