package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoadworkRepositoryTest {
    @Autowired
    RoadworkRepository repository;

    /**
     * Roadwork entities can be persisted in the repository.
     */
    @Test
    public void saved() {
        String CODE = "code1";
        TrafficRoadwork roadwork = TrafficRoadwork.builder()
                                                  .systemCodeNumber(CODE)
                                                  .point(new Point(1L, 1L, 0.0, 0.0))
                                                  .build();

        TrafficRoadwork savedRoadwork = repository.save(roadwork);
        assertThat(savedRoadwork.getSystemCodeNumber()).isEqualTo(CODE);

        assertThat(repository.count()).isEqualTo(1);
    }
}