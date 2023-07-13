package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CameraRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceCamera extends AbstractTrafficDataService<Camera> {
    public TrafficDataServiceCamera(CameraRepository repository) {
        super(repository, Camera.class);
    }
}
