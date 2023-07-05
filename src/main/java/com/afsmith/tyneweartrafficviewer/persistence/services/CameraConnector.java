package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.CameraDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.CameraMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.CameraRepository;
import org.springframework.stereotype.Component;

@Component
public class CameraConnector extends TrafficDataTypeConnector<Camera, CameraDTO, String>{
    public CameraConnector(CameraRepository repository, CameraMapper mapper) {
        super(repository, mapper, CameraDTO.class, Camera.class);
    }
}
