package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointDataRepository<T extends TrafficPointData> extends JpaRepository<T, String> {
}
