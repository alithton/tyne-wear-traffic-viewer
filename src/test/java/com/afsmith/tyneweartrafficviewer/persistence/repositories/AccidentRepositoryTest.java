package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.Point;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficAccident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccidentRepositoryTest {

    @Autowired
    AccidentRepository repository;

    @Test
    public void saved() {
        String code = "code1";
        TrafficAccident accident = TrafficAccident.builder()
                                                  .systemCodeNumber(code)
                                                  .point(new Point(1L, 1L, 0.0, 0.0))
                                                  .build();
        TrafficAccident savedAccident = repository.save(accident);

        assertThat(savedAccident).isNotNull();
        assertThat(savedAccident.getSystemCodeNumber()).isEqualTo(code);
        assertThat(savedAccident.getPoint().getLatitude()).isEqualTo(0.0);
    }

}