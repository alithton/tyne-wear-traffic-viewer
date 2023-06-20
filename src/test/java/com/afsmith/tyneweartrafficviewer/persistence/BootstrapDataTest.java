package com.afsmith.tyneweartrafficviewer.persistence;

import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BootstrapDataTest {

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TrafficDataPersistence dataPersistence;

    @Autowired
    BootstrapData bootstrapData;

    @Test
    void run() throws Exception {
        bootstrapData.run();
        assertEquals(incidentRepository.count(), 221);
        assertEquals(eventRepository.count(), 48);
    }
}