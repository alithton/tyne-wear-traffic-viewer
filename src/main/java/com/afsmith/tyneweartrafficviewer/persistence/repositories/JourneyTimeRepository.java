package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A data repository, managing access to traffic journey time data.
 */
public interface JourneyTimeRepository extends JpaRepository<JourneyTime, String> {
}
