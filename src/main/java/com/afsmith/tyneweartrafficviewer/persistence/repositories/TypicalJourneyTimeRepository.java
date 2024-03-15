package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

/**
 * A data repository, managing access to typical journey time data.
 */
public interface TypicalJourneyTimeRepository extends CrudRepository<TypicalJourneyTime, Long> {

    /**
     * Get typical journey time data for the time matching the provided time. The
     * data is divided between weekday journey times and weekend. If isWeekend is
     * true, get the weekend data, otherwise get the weekday data.
     * @param timeofDay The time of day for which typical journey time data should be retrieved.
     * @param isWeekend Whether data for weekend or weekday journey times should be retrieved.
     * @return A list of typical journey time data matching the query criteria.
     */
    @Query("select t from TypicalJourneyTime t where t.timeOfDay = :timeOfDay and t.isWeekend = :isWeekend")
    List<TypicalJourneyTime> findByTimeAndWeekend(@Param("timeOfDay") LocalTime timeofDay,
                                                  @Param("isWeekend") boolean isWeekend);
}
