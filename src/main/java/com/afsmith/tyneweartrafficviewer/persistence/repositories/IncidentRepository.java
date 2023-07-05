package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for traffic incident data.
 */
public interface IncidentRepository extends JpaRepository<TrafficIncident, String> {
}
