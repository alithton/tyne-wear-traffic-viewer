package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("incidentService")
@RequiredArgsConstructor
public class TrafficDataServiceIncidents implements TrafficDataService<TrafficIncidentDTO> {
    private final IncidentRepository incidentRepository;
    private final TrafficIncidentMapper incidentMapper;
    @Override
    public List<TrafficIncidentDTO> listAll() {
        return incidentMapper.entityToDto(incidentRepository.findAll());
    }
}
