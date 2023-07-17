package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.springframework.stereotype.Service;

@Service
public class IncidentDataConversionService extends AbstractDataConversionService<TrafficIncident, TrafficIncidentDTO> {
    public IncidentDataConversionService(TrafficIncidentMapper mapper) {
        super(mapper);
    }
}
