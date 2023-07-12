package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.CameraDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.CameraMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CameraRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceCamera extends AbstractTrafficDataService<Camera, CameraDTO, String> {
    public TrafficDataServiceCamera(CameraMapper mapper, CameraRepository repository) {
        super(mapper, repository, CameraDTO.class, Camera.class);
    }
}
