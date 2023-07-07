package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JourneyTimeRepositoryTest {
    @Autowired
    JourneyTimeRepository repository;

    @Test
    public void save() {
        JourneyTime journeyTime = MockData.getJourneyTimeWithRoute("code1");
        JourneyTime savedJourneyTime = repository.save(journeyTime);

        assertThat(savedJourneyTime).isNotNull();
        assertThat(savedJourneyTime.getRoute()).isNotNull();
        assertThat(repository.count()).isEqualTo(1);
    }

}