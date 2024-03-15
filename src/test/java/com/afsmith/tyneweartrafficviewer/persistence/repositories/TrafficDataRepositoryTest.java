package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TrafficDataRepositoryTest {

    @Autowired
    TrafficDataRepository repository;

    List<TrafficData> entities;

    @BeforeEach
    void setUp() {
         entities = List.of(
                MockData.getJourneyTimeWithRoute("1"),
                MockData.getIncident("2"),
                MockData.getEvent("3"),
                MockData.getCamera("4"),
                MockData.getAccident("5"),
                MockData.getRoadwork("6"),
                MockData.getTypicalJourneyTime("7")
        );
    }

    @Test
    void save_SavesTypicalJourneyTimeData_WhenValidDataProvided() {
        TypicalJourneyTime typicalJourneyTime = MockData.getTypicalJourneyTime("1");
        repository.save(typicalJourneyTime);
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void saveAll_SavesAllEntitiesInRepository_WhenListOfEntitiesProvided() {
        repository.saveAll(entities);
        assertThat(repository.count()).isEqualTo(entities.size());
    }

    @Test
    void findByDataType_FindsExpectedNumberOfAccidents_WhenAccidentTypeProvided() {
        repository.saveAll(entities);
        List<TrafficAccident> accidents = repository.findByDataType(TrafficAccident.class);
        assertThat(accidents.size()).isEqualTo(1);
        assertThat(accidents.get(0)).isInstanceOf(TrafficAccident.class);

        assertThat(accidents.get(0).getSystemCodeNumber()).isEqualTo("5");
    }

    @Test
    void findByDataType_FindsExpectedNumberOfJourneyTimes_WhenJourneyTimeTypeProvided() {
        repository.saveAll(entities);
        List<JourneyTime> journeyTimes = repository.findByDataType(JourneyTime.class);
        assertThat(journeyTimes.size()).isEqualTo(1);
        assertThat(journeyTimes.get(0).getRoute()).isNotNull();
    }

    @Test
    void findByCodeNumber_FindsEntityWithMatchingCodeNumber_WhenValidCodeNumberProvided() {
        repository.saveAll(entities);
        TrafficData roadwork = repository.findByCodeNumber("6");

        assertThat(roadwork).isNotNull();
        assertThat(roadwork.getSystemCodeNumber()).isEqualTo("6");
        assertThat(roadwork).isInstanceOf(TrafficRoadwork.class);
    }

    @Test
    void findPointDataByCodeNumber_FindsMatchingPointData_WhenValidCodeNumberProvided() {
        repository.saveAll(entities);
        TrafficPointData roadwork = repository.findPointDataByCodeNumber("6");

        assertThat(roadwork).isNotNull();
        assertThat(roadwork.getSystemCodeNumber()).isEqualTo("6");
        assertThat(roadwork).isInstanceOf(TrafficRoadwork.class);
    }

    @Test
    void findPointDataByCodeNumber_ReturnsNull_WhenCodeNumberInvalid() {
        repository.saveAll(entities);
        TrafficPointData roadwork = repository.findPointDataByCodeNumber(null);
        assertThat(roadwork).isNull();
    }

    @Test
    void findPointDataByCodeNumber_ReturnsNull_WhenCodeNumberIsForNonPointData() {
        repository.saveAll(entities);
        TrafficPointData pointData = repository.findPointDataByCodeNumber("1");
        assertThat(pointData).isNull();
    }

    @Test
    void findPointDataByCodeNumber_ReturnsNull_WhenCodeNumberNotFound() {
        repository.saveAll(entities);
        TrafficPointData pointData = repository.findPointDataByCodeNumber("");
        assertThat(pointData).isNull();
    }
}