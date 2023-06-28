package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficRoadworkMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.RoadworkRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficRoadworkConnector extends TrafficDataTypeConnector<TrafficRoadwork, TrafficRoadworksDTO, String>{
    public TrafficRoadworkConnector(RoadworkRepository repository, TrafficRoadworkMapper mapper) {
        super(repository, mapper, TrafficRoadworksDTO.class, TrafficRoadwork.class);
    }
}
