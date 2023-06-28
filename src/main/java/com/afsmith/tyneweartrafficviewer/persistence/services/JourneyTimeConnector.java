package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.JourneyTimeRepository;
import org.springframework.stereotype.Component;

@Component
public class JourneyTimeConnector extends TrafficDataTypeConnector<JourneyTime, JourneyTimeDTO, String>{
    public JourneyTimeConnector(JourneyTimeRepository repository, JourneyTimeMapper mapper) {
        super(repository, mapper, JourneyTimeDTO.class, JourneyTime.class);
    }
}
