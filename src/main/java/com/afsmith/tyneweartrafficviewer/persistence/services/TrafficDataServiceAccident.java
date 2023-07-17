package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.AccidentRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceAccident extends AbstractTrafficDataService<TrafficAccident> {

    public TrafficDataServiceAccident(AccidentRepository repository) {
        super(repository, TrafficAccident.class);
    }
}
