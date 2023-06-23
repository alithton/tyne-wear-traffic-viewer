package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for traffic event data.
 */
public interface EventRepository extends JpaRepository<TrafficEvent, String> {
}