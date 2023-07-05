package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficAccidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.AccidentRepository;
import org.springframework.stereotype.Component;

@Component
public class TrafficAccidentConnector extends TrafficDataTypeConnector<TrafficAccident, TrafficAccidentDTO, String>{
    public TrafficAccidentConnector(AccidentRepository repository, TrafficAccidentMapper mapper) {
        super(repository, mapper, TrafficAccidentDTO.class, TrafficAccident.class);
    }
}
