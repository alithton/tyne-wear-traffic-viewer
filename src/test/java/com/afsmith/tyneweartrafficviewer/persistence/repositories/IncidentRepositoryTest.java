package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IncidentRepositoryTest {
    @Autowired
    IncidentRepository incidentRepository;

    /**
     * Test that traffic incidents can be successfully saved to the repository.
     */
    @Test
    public void save() {
        TrafficIncident incident = TrafficIncident.builder()
                                                  .systemCodeNumber("code1")
                                                  .point(new Point(1L, 1L, 0.0, 0.0))
                                                  .build();

        TrafficIncident savedIncident = incidentRepository.save(incident);
        assertNotNull(savedIncident);
        assertNotNull(savedIncident.getSystemCodeNumber());
        assertNotEquals(0, incidentRepository.count());
    }
}