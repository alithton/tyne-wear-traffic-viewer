package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficEventMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceEvent extends AbstractTrafficDataService<TrafficEvent, TrafficEventDTO, String> {

    public TrafficDataServiceEvent(TrafficEventMapper mapper, EventRepository repository) {
        super(mapper, repository, TrafficEventDTO.class, TrafficEvent.class);
    }
}
