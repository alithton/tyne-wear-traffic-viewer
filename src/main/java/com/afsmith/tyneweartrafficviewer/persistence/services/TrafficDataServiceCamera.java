package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CameraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Provides data access services for traffic camera data.
 */
@Service
public class TrafficDataServiceCamera extends AbstractTrafficDataService<Camera> {
    public TrafficDataServiceCamera(CameraRepository repository) {
        super(repository, Camera.class);
    }

    /**
     * Find the data for the camera specified by the provided system code number.
     * If the code number does not match any cameras in the database, throw an error.
     * @param systemCodeNumber The ID of the camera to be retrieved.
     * @return The found camera data.
     */
    public Camera findById(String systemCodeNumber) {
        return repository.findById(systemCodeNumber)
                         .orElseThrow(() -> new EntityNotFoundException(
                                 String.format("Camera with code number %s not found.", systemCodeNumber)
                         ));
    }
}
