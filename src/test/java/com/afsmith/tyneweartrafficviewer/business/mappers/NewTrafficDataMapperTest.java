package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static com.afsmith.tyneweartrafficviewer.business.mappers.NewTrafficDataMapper.convert;
import static org.assertj.core.api.Assertions.assertThat;

class NewTrafficDataMapperTest {

    @Test
    void convertToPointData() {
        TrafficDataTypes dataType = TrafficDataTypes.INCIDENT;
        NewTrafficDataDTO newData = MockData.getNewTrafficDataDto(dataType);
        TrafficPointData pointData = convert(newData).toPointData();
        ZonedDateTime currentTime = ZonedDateTime.now();

        assertThat(pointData).isNotNull();

        assertThat(pointData.getSystemCodeNumber()).contains(String.valueOf(newData.hashCode()));

        assertThat(pointData.getPoint().getLatitude()).isEqualTo(MockData.LATITUDE);
        assertThat(pointData.getPoint().getLongitude()).isEqualTo(MockData.LONGITUDE);
        assertThat(pointData.getType()).isEqualTo(dataType);
        assertThat(pointData.getDataSourceTypeRef()).isEqualTo("User");
        assertThat(pointData.getCreationDate().getMinute()).isEqualTo(currentTime.getMinute());
    }

    @Test
    void convertToIncident() {
        NewTrafficDataDTO newIncident = MockData.getNewTrafficDataDto(TrafficDataTypes.INCIDENT);

        TrafficIncident incident = convert(newIncident).toIncident();

        assertThat(incident).isNotNull();
        assertThat(incident.getIncidentTime()).isEqualTo(MockData.TIME);
        assertThat(incident.getEndTime()).isEqualTo(MockData.END_TIME);
    }

    @Test
    void convertToEvent() {
        NewTrafficDataDTO newEvent = MockData.getNewTrafficDataDto(TrafficDataTypes.EVENT);

        TrafficEvent event = convert(newEvent).toEvent();

        assertThat(event).isNotNull();
        assertThat(event.getPlanned().getStartTime()).isEqualTo(MockData.TIME);
        assertThat(event.getPlanned().getEndTime()).isEqualTo(MockData.END_TIME);
    }

    @Test
    void convertToAccident() {
        NewTrafficDataDTO newAccident = MockData.getNewTrafficDataDto(TrafficDataTypes.ACCIDENT);
        TrafficAccident accident = convert(newAccident).toAccident();

        assertThat(accident).isNotNull();
        assertThat(accident.getAccidentTime()).isEqualTo(MockData.TIME);
        assertThat(accident.getEndTime()).isEqualTo(MockData.END_TIME);
    }

    @Test
    void convertToRoadwork() {
        NewTrafficDataDTO newRoadwork = MockData.getNewTrafficDataDto(TrafficDataTypes.ROADWORKS);
        TrafficRoadwork roadwork = convert(newRoadwork).toRoadwork();

        assertThat(roadwork).isNotNull();
        assertThat(roadwork.getActual().getStartTime()).isEqualTo(MockData.TIME);
        assertThat(roadwork.getActual().getEndTime()).isEqualTo(MockData.END_TIME);
        assertThat(roadwork.getPlanned()).isNull();
    }
}