package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficAccidentExternalTest {

    @Test
    void toEntity() {
        String code = "code1";
        TrafficAccidentExternal externalAccident = TrafficAccidentExternal.builder()
                                                                          .systemCodeNumber(code)
                                                                          .build();
        TrafficAccident accident = externalAccident.toEntity();

        assertThat(accident).isNotNull();
        assertThat(accident.getSystemCodeNumber()).isEqualTo(code);
    }
}