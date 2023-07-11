package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficAccidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.AccidentRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceAccident extends AbstractTrafficDataService<TrafficAccident, TrafficAccidentDTO, String> {

    public TrafficDataServiceAccident(TrafficAccidentMapper mapper, AccidentRepository repository) {
        super(mapper, repository, TrafficAccidentDTO.class, TrafficAccident.class);
    }
}
