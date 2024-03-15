package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TypicalJourneyTimeRepositoryTest {

    @Autowired
    TypicalJourneyTimeRepository repository;

    @Test
    void findByTimeAndWeekend_ReturnsDataMatchingCriteria_WhenValidDataProvided() {
        List<TypicalJourneyTime> typicalJTs = List.of(MockData.getTypicalJourneyTime("1"),
                                                      MockData.getTypicalJourneyTime("2"));
        typicalJTs.get(1).setWeekend(true);

        repository.saveAll(typicalJTs);

        List<TypicalJourneyTime> matches = repository.findByTimeAndWeekend(LocalTime.NOON, false);
        assertThat(matches).isNotNull();
        assertThat(matches.size()).isEqualTo(1);
    }

    @Test
    void findByTimeAndWeekend_ReturnsEmptyList_WhenNoDataMatchCriteria() {
        List<TypicalJourneyTime> typicalJTs = List.of(MockData.getTypicalJourneyTime("1"),
                                                      MockData.getTypicalJourneyTime("2"));
        typicalJTs.get(1).setWeekend(true);

        repository.saveAll(typicalJTs);

        List<TypicalJourneyTime> matches = repository.findByTimeAndWeekend(LocalTime.MIDNIGHT, true);
        assertThat(matches).isNotNull();
        assertThat(matches).isEmpty();
    }
}