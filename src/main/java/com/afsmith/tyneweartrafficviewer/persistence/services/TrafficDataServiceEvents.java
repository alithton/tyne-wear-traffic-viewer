package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficEventMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

@RequiredArgsConstructor
@Service("eventsService")
public class TrafficDataServiceEvents implements TrafficDataService {

    private final EventRepository repository;
    private final TrafficEventMapper mapper;

    @Override
    public List<TrafficDataDTO> listAll() {
        return List.copyOf(mapper.entityToDto(repository.findAll()));
    }

    @Override
    public void persist(List<TrafficDataDTO> trafficData) {
        List<TrafficEventDTO> eventDTOS = downcastList(trafficData, TrafficEventDTO.class);
        List<TrafficEvent> events = mapper.dtoToEntity(eventDTOS);
        repository.saveAll(events);
    }
}
