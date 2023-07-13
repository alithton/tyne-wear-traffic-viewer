package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CameraDTO;
import com.afsmith.tyneweartrafficviewer.entities.Camera;
import org.mapstruct.Mapper;

@Mapper
public interface CameraMapper extends TrafficDataMapper<CameraDTO, Camera>{
}
