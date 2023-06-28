package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficAccidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficEventMapper;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficRoadworkMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.AccidentRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.EventRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.RoadworkRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@AutoConfigureTestDatabase
@ActiveProfiles("test")
class TrafficDataPersistenceRetrievalTest {

    TrafficDataPersistence dataPersistence;

    TrafficIncidentConnector incidentConnector;
    TrafficEventConnector eventConnector;
    TrafficAccidentConnector accidentConnector;
    TrafficRoadworkConnector roadworkConnector;
    @Autowired
    JourneyTimeConnector journeyTimeConnector;

    // Mappers for each data type
    @Autowired
    TrafficIncidentMapper incidentMapper;
    @Autowired
    TrafficEventMapper eventMapper;
    @Autowired
    TrafficAccidentMapper accidentMapper;
    @Autowired
    TrafficRoadworkMapper roadworkMapper;

    // Mocked repositories for each data type
    @MockBean
    IncidentRepository incidentRepository;
    @MockBean
    EventRepository eventRepository;
    @MockBean
    AccidentRepository accidentRepository;
    @MockBean
    RoadworkRepository roadworkRepository;

    @Autowired
    TrafficDataService dataService;

    @BeforeEach
    void setUp() {
        incidentConnector = new TrafficIncidentConnector(incidentRepository, incidentMapper);
        eventConnector = new TrafficEventConnector(eventRepository, eventMapper);
        accidentConnector = new TrafficAccidentConnector(accidentRepository, accidentMapper);
        roadworkConnector = new TrafficRoadworkConnector(roadworkRepository, roadworkMapper);
        dataPersistence = new TrafficDataPersistence(incidentConnector, eventConnector, accidentConnector,
                                                     roadworkConnector, journeyTimeConnector, dataService);

        // Set up mock repositories to return mocked data
        when(incidentRepository.findAll())
                .thenReturn(List.of(MockData.getIncident("code1"),
                                    MockData.getIncident("code2")));
        when(eventRepository.findAll())
                .thenReturn(List.of(MockData.getEvent("code1"),
                                     MockData.getEvent("code2")));
        when(accidentRepository.findAll())
                .thenReturn(List.of(MockData.getAccident("code1"),
                                    MockData.getAccident("code2")));
        when(roadworkRepository.findAll())
                .thenReturn(List.of(MockData.getRoadwork("code1"),
                                    MockData.getRoadwork("code2")));

    }

    @Test
    void listAllIncidents() {
        List<TrafficDataDTO> incidents = dataPersistence.listAll(TrafficDataTypes.INCIDENT);
        assertThat(incidents).isNotNull();
        assertThat(incidents.size()).isEqualTo(2);
        assertThat(incidents.get(0)).isInstanceOf(TrafficIncidentDTO.class);
    }

    @Test
    void listAllEvents() {
        List<TrafficDataDTO> incidents = dataPersistence.listAll(TrafficDataTypes.EVENT);
        assertThat(incidents).isNotNull();
        assertThat(incidents.size()).isEqualTo(2);
        assertThat(incidents.get(0)).isInstanceOf(TrafficEventDTO.class);
    }

    @Test
    void listAllAccidents() {
        List<TrafficDataDTO> incidents = dataPersistence.listAll(TrafficDataTypes.ACCIDENT);
        assertThat(incidents).isNotNull();
        assertThat(incidents.size()).isEqualTo(2);
        assertThat(incidents.get(0)).isInstanceOf(TrafficAccidentDTO.class);
    }

    @Test
    void listAllRoadworks() {
        List<TrafficDataDTO> incidents = dataPersistence.listAll(TrafficDataTypes.ROADWORKS);
        assertThat(incidents).isNotNull();
        assertThat(incidents.size()).isEqualTo(2);
        assertThat(incidents.get(0)).isInstanceOf(TrafficRoadworksDTO.class);
    }


}