package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TypicalJourneyTimeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

/**
 * A traffic data service providing access to typical journey time data.
 */
@Service
public class TrafficDataServiceTypicalJourneyTime extends AbstractTrafficDataService<TypicalJourneyTime> {

    // A reader for reading typical journey time data from file.
    private final TypicalJourneyTimeReader reader = new TypicalJourneyTimeReader();

    public TrafficDataServiceTypicalJourneyTime(TypicalJourneyTimeRepository repository) {
        super(repository, TypicalJourneyTime.class);
    }

    /**
     * Load typical journey time data from file. This function expects that the data is
     * stored in two files: one containing weekday data and the other weekend data.
     * The returned list merges data from both files.
     * @param weekdayFileName Path to the file containing weekday average journey time data.
     * @param weekendFileName Path to the file containing weekend average journey time data.
     * @return Combined typical journey time data from both weekdays and weekends.
     */
    public List<TypicalJourneyTime> loadFromFile(String weekdayFileName, String weekendFileName) throws IOException {
        List<TypicalJourneyTime> weekdays = reader.read(weekdayFileName, false);
        List<TypicalJourneyTime> weekends = reader.read(weekendFileName, true);

        return Stream.concat(weekdays.stream(), weekends.stream())
                     .toList();
    }

    /**
     * Find all typical journey time values recorded for the given time of day.
     * @param time The time to be retrieved.
     * @param isWeekend Whether weekend or weekday data should be retrieved.
     * @return A list of typical journey time data matching the criteria.
     */
    public List<TypicalJourneyTime> findByTime(LocalTime time, boolean isWeekend) {
        LocalTime roundedTime = roundToNearest5Minutes(time);
        TypicalJourneyTimeRepository typicalJourneyTimeRepository = (TypicalJourneyTimeRepository) repository;
        return typicalJourneyTimeRepository.findByTimeAndWeekend(roundedTime, isWeekend);
    }

    /**
     * Find all typical journey time values recorded for the given time of day.
     * @param time The time to be retrieved. Should be in a format that can be processed
     *             by LocalTime.parse(String).
     * @param isWeekend Whether weekend or weekday data should be retrieved.
     * @return A list of typical journey time data matching the criteria.
     */
    public List<TypicalJourneyTime> findByTime(String time, boolean isWeekend) {
        LocalTime localTime = LocalTime.parse(time);
        return findByTime(localTime, isWeekend);
    }

    /*
     * Round the provided time to the nearest 5 minutes. Rounds down at the midpoint.
     */
    private LocalTime roundToNearest5Minutes(LocalTime time) {
        int fiveMinuteIntervals = time.getMinute() / 5;
        double fractionalMinutes = getFractionalMinutes(time);
        double remainingMinutes = fractionalMinutes % 5;

        int roundedMinutes = 5 * (remainingMinutes > 2.5 ? fiveMinuteIntervals + 1
                                                         : fiveMinuteIntervals);

        LocalTime truncatedTime = time.truncatedTo(ChronoUnit.HOURS);
        return truncatedTime.plusMinutes(roundedMinutes);
    }

    // Calculate the fractional minutes for the provided time object using seconds.
    // For example 02:30 -> 2.5 minutes
    private double getFractionalMinutes(LocalTime time) {
        int minutes = time.getMinute();
        double seconds = time.getSecond();
        return minutes + seconds / 60;
    }
}
