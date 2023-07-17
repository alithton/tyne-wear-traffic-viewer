package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.RoadworkRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceRoadworks extends AbstractTrafficDataService<TrafficRoadwork> {
    public TrafficDataServiceRoadworks(RoadworkRepository repository) {
        super(repository, TrafficRoadwork.class);
    }
}
