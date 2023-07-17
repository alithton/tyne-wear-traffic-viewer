package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TypicalJourneyTimeReaderTest {

    TypicalJourneyTimeReader reader = new TypicalJourneyTimeReader();

    @Test
    void readWeekdayDataFromFile() throws IOException, CsvValidationException {
        String dataPath = "/src/test/resources/data/journey-time-weekday-test.csv";
        List<TypicalJourneyTime> typicalJourneyTimes = reader.read(dataPath, false);

        System.out.println(typicalJourneyTimes);

        assertThat(typicalJourneyTimes.size()).isEqualTo(4);

        for (TypicalJourneyTime typicalJourneyTime : typicalJourneyTimes) {
            assertThat(typicalJourneyTime.getSystemCodeNumber()).isNotEmpty();
            assertThat(typicalJourneyTime.isWeekend()).isFalse();
            assertThat(typicalJourneyTime.getTimeOfDay()).hasHour(9);
        }
    }

    @Test
    void readWeekendDataFromFile() throws IOException {
        String dataPath = "/src/test/resources/data/journey-time-weekend-test.csv";
        List<TypicalJourneyTime> typicalJourneyTimes = reader.read(dataPath, true);
        System.out.println(typicalJourneyTimes);

        assertThat(typicalJourneyTimes.size()).isEqualTo(7);

        for (var typicalJourneyTime : typicalJourneyTimes) {
            assertThat(typicalJourneyTime.getSystemCodeNumber()).isNotEmpty();
            assertThat(typicalJourneyTime.isWeekend()).isTrue();
            assertThat(typicalJourneyTime.getTimeOfDay()).hasHour(0);
        }
    }

    @Test
    void readDoublesWithThousandsSeparator() throws IOException {
        String dataPath = "src/test/resources/data/journey-time-large-doubles.csv";
        List<TypicalJourneyTime> typicalJourneyTimes = reader.read(dataPath, false);

        assertThat(typicalJourneyTimes.size()).isEqualTo(1);
        TypicalJourneyTime value = typicalJourneyTimes.get(0);

        assertThat(value.getTravelTime()).isEqualTo(1039.25);
    }

    @Test
    void readIgnoresSubsectionHeadings() throws IOException {
        String dataPath = "src/test/resources/data/journey-time-subsection-heading.csv";
        List<TypicalJourneyTime> typicalJourneyTimes = reader.read(dataPath, true);

        assertThat(typicalJourneyTimes.size()).isEqualTo(2);
    }

}