package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficPointDataDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.exceptions.InvalidTrafficDataException;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;

import java.util.List;

public interface DtoService {
    <DTO extends MappableDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType);

    <DTO extends MappableDTO, T extends TrafficEntity> List<DTO> listAll(TrafficDataTypes dataType, FilterService filter);

    byte[] getImage(String systemCodeNumber);

    <DTO extends TrafficPointDataDTO, T extends TrafficPointData> DTO getIncident(String codeNumber)
            throws DataNotFoundException;

    void save(NewTrafficDataDTO trafficData, String token)
            throws NotAuthenticatedException, InvalidTrafficDataException;
}
