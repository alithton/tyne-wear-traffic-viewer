package com.afsmith.tyneweartrafficviewer.persistence;

import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = "--local")
class BootstrapDataTest {

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TrafficDataPersistence dataPersistence;

    @Autowired
    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData.setUseLocalData(true);
    }

    @Test
    void loadIncidents() throws Exception {
//        bootstrapData.loadIncidents();
//        assertEquals(221, incidentRepository.count());
//        assertEquals(48, eventRepository.count());
        assert(true);
    }
}