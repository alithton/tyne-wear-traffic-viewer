package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.util.List;

public interface DtoService {
    <DTO extends MappableDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType);

    byte[] getImage(String systemCodeNumber);
}
