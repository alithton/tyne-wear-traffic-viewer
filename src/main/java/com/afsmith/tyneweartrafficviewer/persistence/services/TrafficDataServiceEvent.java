package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceEvent extends AbstractTrafficDataService<TrafficEvent> {

    public TrafficDataServiceEvent(EventRepository repository) {
        super(repository, TrafficEvent.class);
    }
}
