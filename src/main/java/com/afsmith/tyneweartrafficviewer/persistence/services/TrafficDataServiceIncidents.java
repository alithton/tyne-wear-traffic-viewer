package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

/**
 * A service to store and access traffic incident data.
 */
@Service("incidentService")
@RequiredArgsConstructor
public class TrafficDataServiceIncidents implements TrafficDataService {
    private final IncidentRepository incidentRepository;
    private final TrafficIncidentMapper incidentMapper;

    @Override
    public List<TrafficDataDTO> listAll() {
        return List.copyOf(incidentMapper.entityToDto(incidentRepository.findAll()));
    }

    @Override
    public void persist(List<TrafficDataDTO> trafficData) {
        List<TrafficIncidentDTO> incidentData = downcastList(trafficData, TrafficIncidentDTO.class);
        List<TrafficIncident> trafficIncidents = incidentMapper.dtoToEntity(incidentData);
        incidentRepository.saveAll(trafficIncidents);
    }
}
