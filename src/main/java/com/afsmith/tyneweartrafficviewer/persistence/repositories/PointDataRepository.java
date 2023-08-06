package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A data repository, managing access to all types of traffic incident data.
 * @param <T> The type of traffic incident data.
 */
public interface PointDataRepository<T extends TrafficPointData> extends JpaRepository<T, String> {
}
