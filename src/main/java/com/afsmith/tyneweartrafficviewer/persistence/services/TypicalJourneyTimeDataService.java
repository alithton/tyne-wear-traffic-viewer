package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

/**
 * A data service for accessing typical journey time data.
 */
public interface TypicalJourneyTimeDataService {

    /**
     * Find typical journey time data for the provided time.
     * @param time The time of day.
     * @param isWeekend Whether weekend or weekday data should be retrieved.
     * @return A list of typical journey time data for the specified time.
     */
    List<TypicalJourneyTime> findByTime(LocalTime time, boolean isWeekend);

    /**
     * Load typical journey time data from file. This function expects that the data is
     * stored in two files: one containing weekday data and the other weekend data.
     * The returned list merges data from both files.
     * @param weekdayFileName Path to the file containing weekday average journey time data.
     * @param weekendFileName Path to the file containing weekend average journey time data.
     * @return Combined typical journey time data from both weekdays and weekends.
     */
    List<TypicalJourneyTime> loadFromFile(String weekdayFileName, String weekendFileName) throws IOException;
}
