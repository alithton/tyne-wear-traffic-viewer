package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TrafficDataPersistenceStorageTest {

    @Autowired
    TrafficDataPersistence dataPersistence;

    @Autowired
    IncidentRepository incidentRepository;

    @Rollback
    @Transactional
    @Test
    void persistIncidentEntities() {
        List<TrafficData> incidents = List.of(MockData.getIncident("code1"),
                                              MockData.getIncident("code2"));
        long initialCount = incidentRepository.count();
        dataPersistence.persistEntities(incidents, TrafficDataTypes.INCIDENT);

        assertThat(incidentRepository.count()).isGreaterThan(initialCount);
    }
}
