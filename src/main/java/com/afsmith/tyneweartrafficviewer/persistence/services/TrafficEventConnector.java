package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficEventMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import org.springframework.stereotype.Component;

/**
 * A connector for working with traffic events.
 */
@Component
public class TrafficEventConnector extends TrafficDataTypeConnector<TrafficEvent, TrafficEventDTO, String> {
    public TrafficEventConnector(EventRepository eventRepository, TrafficEventMapper mapper) {
        super(eventRepository, mapper, TrafficEventDTO.class);
    }
}
