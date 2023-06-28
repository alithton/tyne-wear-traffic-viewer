package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.PlannedTimes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficRoadworksExternalTest {

    @Test
    void toEntity() {
        String CODE = "code1";
        ZonedDateTime time = ZonedDateTime.now();
        TrafficRoadworksExternal externalRoadwork = TrafficRoadworksExternal.builder()
                                                                            .systemCodeNumber(CODE)
                                                                            .actual(new PlannedExternal(time, time))
                                                                            .build();

        TrafficRoadwork roadwork = externalRoadwork.toEntity();
        assertThat(roadwork.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(roadwork.getActual()).isInstanceOf(PlannedTimes.class);
        assertThat(roadwork.getActual()
                           .getStartTime()
                           .getYear()).isEqualTo(time.getYear());
    }
}