package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficRoadworkMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.RoadworkRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceRoadworks extends AbstractTrafficDataService<TrafficRoadwork, TrafficRoadworksDTO, String> {
    public TrafficDataServiceRoadworks(TrafficRoadworkMapper mapper, RoadworkRepository repository) {
        super(mapper, repository, TrafficRoadworksDTO.class, TrafficRoadwork.class);
    }
}
