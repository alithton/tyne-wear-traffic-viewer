package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface TypicalJourneyTimeRepository extends JpaRepository<TypicalJourneyTime, String> {

    @Query("select t from TypicalJourneyTime t where t.timeOfDay = :timeOfDay and t.isWeekend = :isWeekend")
    List<TypicalJourneyTime> findByTimeAndWeekend(@Param("timeOfDay") LocalTime timeofDay,
                                                  @Param("isWeekend") boolean isWeekend);
}
