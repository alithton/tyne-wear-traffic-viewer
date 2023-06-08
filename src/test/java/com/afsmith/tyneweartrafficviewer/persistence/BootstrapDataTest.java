package com.afsmith.tyneweartrafficviewer.persistence;

import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapperImpl;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    IncidentRepository incidentRepository;

    TrafficIncidentMapper incidentMapper;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        incidentMapper = new TrafficIncidentMapperImpl();
        bootstrapData = new BootstrapData(incidentRepository, incidentMapper);
    }

    @Test
    void run() throws Exception {
        bootstrapData.run();
        assertEquals(incidentRepository.count(), 221);
    }
}