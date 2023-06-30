package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CameraDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import org.mapstruct.Mapper;

@Mapper
public interface CameraMapper extends TrafficDataMapper<CameraDTO, Camera>{
}
