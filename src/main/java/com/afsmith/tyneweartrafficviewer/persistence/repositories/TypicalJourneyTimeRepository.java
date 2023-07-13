package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TypicalJourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypicalJourneyTimeRepository extends JpaRepository<TypicalJourneyTime, String> {
}
