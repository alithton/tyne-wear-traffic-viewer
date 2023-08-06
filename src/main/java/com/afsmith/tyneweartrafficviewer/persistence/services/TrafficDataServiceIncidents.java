package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

/**
 * A traffic data service providing access to incident data.
 */
@Service
public class TrafficDataServiceIncidents extends AbstractTrafficDataService<TrafficIncident> {
    public TrafficDataServiceIncidents(IncidentRepository repository) {
        super(repository, TrafficIncident.class);
    }
}
