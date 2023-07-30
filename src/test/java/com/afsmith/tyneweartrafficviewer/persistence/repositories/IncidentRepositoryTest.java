package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

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
                                                  .point(new Point(0.0, 0.0))
                                                  .build();

        TrafficIncident savedIncident = incidentRepository.save(incident);
        assertThat(savedIncident).isNotNull();
        assertThat(savedIncident.getSystemCodeNumber()).isNotEmpty();
        assertThat(savedIncident.getCreatedBy()).isNull();
        assertThat(incidentRepository.count()).isGreaterThan(0L);

    }
}