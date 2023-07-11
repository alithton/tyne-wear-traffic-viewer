package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceIncidents extends AbstractTrafficDataService<TrafficIncident, TrafficIncidentDTO, String> {
    public TrafficDataServiceIncidents(TrafficIncidentMapper mapper, IncidentRepository repository) {
        super(mapper, repository, TrafficIncidentDTO.class, TrafficIncident.class);
    }
}
