package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TypicalJourneyTimeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TrafficDataServiceTypicalJourneyTimeTest {
    String WEEKDAY_FILE = "/src/test/resources/data/journey-time-weekday-test.csv";
    String WEEKEND_FILE = "/src/test/resources/data/journey-time-weekend-test.csv";

    TrafficDataServiceTypicalJourneyTime dataService;

    @Autowired
    TypicalJourneyTimeRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new TrafficDataServiceTypicalJourneyTime(repository);
    }

    @Test
    void loadFromFile() throws IOException {

        List<TypicalJourneyTime> typicalJourneyTimes = dataService.loadFromFile(WEEKDAY_FILE, WEEKEND_FILE);

        assertThat(typicalJourneyTimes.size()).isEqualTo(11);

        assertThat(typicalJourneyTimes.stream()
                                      .filter(TypicalJourneyTime::isWeekend)
                                      .count())
                .isEqualTo(7L);

        assertThat(typicalJourneyTimes.stream()
                                      .filter(e -> !e.isWeekend())
                                      .count())
                .isEqualTo(4L);
    }

    @Transactional
    @Test
    void persist() throws IOException {
        setUpDatabase();
        assertThat(repository.count()).isEqualTo(11);
    }

    @Transactional
    @Test
    void findByExactTime() throws IOException {
        setUpDatabase();

        LocalTime queryTime = LocalTime.parse("09:05:00");

        List<TypicalJourneyTime> result = dataService.findByTime(queryTime, false);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTimeOfDay()).isEqualTo(queryTime);
        assertThat(result.get(0).isWeekend()).isFalse();
    }

    @Transactional
    @Test
    void findByInexactTime() throws IOException {
        setUpDatabase();

        LocalTime queryTime = LocalTime.parse("09:03:55");
        LocalTime expectedResultTime = LocalTime.parse("09:05:00");

        List<TypicalJourneyTime> result = dataService.findByTime(queryTime, false);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTimeOfDay()).isEqualTo(expectedResultTime);
        assertThat(result.get(0).isWeekend()).isFalse();
    }

    @Transactional
    @Test
    void findByMidPointTimeOnWeekend() throws IOException {
        setUpDatabase();

        LocalTime queryTime = LocalTime.parse("00:27:30");
        LocalTime expectedResultTime = LocalTime.parse("00:25:00");

        List<TypicalJourneyTime> result = dataService.findByTime(queryTime, true);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTimeOfDay()).isEqualTo(expectedResultTime);
        assertThat(result.get(0).isWeekend()).isTrue();
    }

    private void setUpDatabase() throws IOException {
        List<TypicalJourneyTime> typicalJourneyTimes = dataService.loadFromFile(WEEKDAY_FILE, WEEKEND_FILE);
        dataService.persistEntities(typicalJourneyTimes);
    }

}