package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadworkRepository extends JpaRepository<TrafficRoadwork, String> {
}
