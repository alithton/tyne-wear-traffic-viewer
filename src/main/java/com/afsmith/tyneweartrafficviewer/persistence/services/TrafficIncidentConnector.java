package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import org.springframework.stereotype.Component;

/**
 * A connector for working with traffic incidents.
 */
@Component
public class TrafficIncidentConnector extends TrafficDataTypeConnector<TrafficIncident, TrafficIncidentDTO, String> {
    public TrafficIncidentConnector(IncidentRepository incidentRepository, TrafficIncidentMapper mapper) {
        super(incidentRepository, mapper, TrafficIncidentDTO.class);
    }
}
