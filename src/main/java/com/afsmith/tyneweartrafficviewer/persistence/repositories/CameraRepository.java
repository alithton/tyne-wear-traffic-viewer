package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A data repository, managing access to traffic camera data.
 */
public interface CameraRepository extends JpaRepository<Camera, String> {
}
