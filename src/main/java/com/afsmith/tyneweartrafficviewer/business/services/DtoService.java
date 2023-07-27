package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficPointDataDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;

import java.util.List;

public interface DtoService {
    <DTO extends MappableDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType);

    byte[] getImage(String systemCodeNumber);

    <DTO extends TrafficPointDataDTO, T extends TrafficPointData> DTO getIncident(String codeNumber) throws DataNotFoundException;
}
