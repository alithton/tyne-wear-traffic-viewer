package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyTimeRepository extends JpaRepository<JourneyTime, String> {
}
