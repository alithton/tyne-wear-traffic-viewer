package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.entities.Point;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.*;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.*;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TrafficDataPersistenceRetrievalTest {

    TrafficDataPersistence dataPersistence;

    TrafficDataServiceIncidents incidentService;
    TrafficDataServiceEvent eventService;
    TrafficDataServiceAccident accidentService;
    TrafficDataServiceRoadworks roadworkService;
    TrafficDataServiceJourneyTimes journeyTimeService;
    TrafficDataServiceCamera cameraService;

    // Mappers for each data type
    TrafficIncidentMapper incidentMapper = Mappers.getMapper(TrafficIncidentMapper.class);
    TrafficEventMapper eventMapper = Mappers.getMapper(TrafficEventMapper.class);
    TrafficAccidentMapper accidentMapper = Mappers.getMapper(TrafficAccidentMapper.class);
    TrafficRoadworkMapper roadworkMapper = Mappers.getMapper(TrafficRoadworkMapper.class);
    JourneyTimeMapper journeyTimeMapper = Mappers.getMapper(JourneyTimeMapper.class);
    CameraMapper cameraMapper = Mappers.getMapper(CameraMapper.class);

    // Mocked repositories for each data type
    @MockBean
    IncidentRepository incidentRepository;
    @MockBean
    EventRepository eventRepository;
    @MockBean
    AccidentRepository accidentRepository;
    @MockBean
    RoadworkRepository roadworkRepository;
    @MockBean
    JourneyTimeRepository journeyTimeRepository;
    @MockBean
    CameraRepository cameraRepository;

    List<TrafficIncident> incidents = List.of(MockData.getIncident("code1"),
                                              MockData.getIncident("code2"));

    List<TrafficDataDTO> incidentDTOS = List.of(MockData.getIncidentDto("code1"),
                                                    MockData.getIncidentDto("code2"));
    @MockBean
    RoutingService routingService;

    @Captor
    ArgumentCaptor<List<JourneyTime>> journeyTimeCaptor;

    @BeforeEach
    void setUp() {
        incidentService = new TrafficDataServiceIncidents(incidentMapper, incidentRepository);
        eventService = new TrafficDataServiceEvent(eventMapper, eventRepository);
        accidentService = new TrafficDataServiceAccident(accidentMapper, accidentRepository);
        roadworkService = new TrafficDataServiceRoadworks(roadworkMapper, roadworkRepository);
        journeyTimeService = new TrafficDataServiceJourneyTimes(journeyTimeMapper, journeyTimeRepository, routingService);
        cameraService = new TrafficDataServiceCamera(cameraMapper, cameraRepository);

        dataPersistence = new TrafficDataPersistence(incidentService, eventService, accidentService,
                                                     roadworkService, journeyTimeService, cameraService);

        // Set up mock repositories to return mocked data
        when(incidentRepository.findAll())
                .thenReturn(incidents);
        when(eventRepository.findAll())
                .thenReturn(List.of(MockData.getEvent("code1"),
                                     MockData.getEvent("code2")));
        when(accidentRepository.findAll())
                .thenReturn(List.of(MockData.getAccident("code1"),
                                    MockData.getAccident("code2")));
        when(roadworkRepository.findAll())
                .thenReturn(List.of(MockData.getRoadwork("code1"),
                                    MockData.getRoadwork("code2")));
        when(journeyTimeRepository.findAll())
                .thenReturn(List.of(MockData.getJourneyTime("code1"),
                                    MockData.getJourneyTime("code2")));
        when(cameraRepository.findAll())
                .thenReturn(List.of(MockData.getCamera("code1"),
                                    MockData.getCamera("code2")));

        when(routingService.calculateRoute(any(Point.class), any(Point.class)))
                .thenReturn(MockData.getSimpleRoute());

    }

    @Test
    void listAllIncidents() {
        testListAll(TrafficDataTypes.INCIDENT, TrafficIncidentDTO.class);
    }

    @Test
    void listAllEvents() {
        testListAll(TrafficDataTypes.EVENT, TrafficEventDTO.class);
    }

    @Test
    void listAllAccidents() {
        testListAll(TrafficDataTypes.ACCIDENT, TrafficAccidentDTO.class);
    }

    @Test
    void listAllRoadworks() {
        testListAll(TrafficDataTypes.ROADWORKS, TrafficRoadworksDTO.class);
    }

    @Test
    void listAllJourneyTimes() {
        testListAll(TrafficDataTypes.SPEED, JourneyTimeDTO.class);
    }

    @Test
    void listAllCamera() {
        testListAll(TrafficDataTypes.CAMERA, CameraDTO.class);
    }

    @Test
    void persistIncidents() {
        dataPersistence.persist(incidentDTOS, TrafficDataTypes.INCIDENT);
        verify(incidentRepository, times(1)).saveAll(anyList());
    }

    // Ensure that the routing service is being used to set a route for journeytime entities without existing
    // route information.
    @Test
    void persistJourneyTimeEntities() {
        List<TrafficData> journeyTimes = List.of(MockData.getJourneyTime("code1"),
                                                 MockData.getJourneyTime("code2"));

        dataPersistence.persistEntities(journeyTimes, TrafficDataTypes.SPEED);

        verify(journeyTimeRepository, times(1)).saveAll(journeyTimeCaptor.capture());

        List<JourneyTime> capturedJourneyTimes = journeyTimeCaptor.getValue();
        assertThat(capturedJourneyTimes.size()).isEqualTo(2);
        assertThat(capturedJourneyTimes.get(0).getRoute()).isNotNull();
    }

    private void testListAll(TrafficDataTypes dataType, Class<? extends TrafficDataDTO> expectedClass) {
        List<TrafficDataDTO> trafficData = dataPersistence.listAll(dataType);
        assertThat(trafficData).isNotNull();
        assertThat(trafficData.size()).isEqualTo(2);
        assertThat(trafficData.get(0)).isInstanceOf(expectedClass);
    }


}