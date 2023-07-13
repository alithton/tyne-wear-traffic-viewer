package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypicalJourneyTimeRepository extends JpaRepository<TypicalJourneyTime, String> {
}
