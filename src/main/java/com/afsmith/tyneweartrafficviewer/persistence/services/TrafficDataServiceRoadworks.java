package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.RoadworkRepository;
import org.springframework.stereotype.Service;

/**
 * A traffic data service providing access to roadwork data.
 */
@Service
public class TrafficDataServiceRoadworks extends AbstractTrafficDataService<TrafficRoadwork> {
    public TrafficDataServiceRoadworks(RoadworkRepository repository) {
        super(repository, TrafficRoadwork.class);
    }
}
