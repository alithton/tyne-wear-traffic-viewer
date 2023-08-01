package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessServiceImpl;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReaderImpl;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.*;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

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
    TrafficDataServiceTypicalJourneyTime typicalJourneyTimeService;
    TrafficPointDataService pointDataService;

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
    @MockBean
    TypicalJourneyTimeRepository typicalJourneyTimeRepository;
    @MockBean
    PointDataRepository<TrafficPointData> pointDataRepository;
    @MockBean
    CommentRepository commentRepository;

    List<TrafficIncident> incidents = List.of(MockData.getIncident("code1"),
                                              MockData.getIncident("code2"));

    @MockBean
    RoutingService routingService;

    @MockBean
    ExternalDataAccessService dataAccessService;

    @Captor
    ArgumentCaptor<List<JourneyTime>> journeyTimeCaptor;

    @BeforeEach
    void setUp() {
        incidentService = new TrafficDataServiceIncidents(incidentRepository);
        eventService = new TrafficDataServiceEvent(eventRepository);
        accidentService = new TrafficDataServiceAccident(accidentRepository);
        roadworkService = new TrafficDataServiceRoadworks(roadworkRepository);
        journeyTimeService = new TrafficDataServiceJourneyTimes(journeyTimeRepository, routingService);
        cameraService = new TrafficDataServiceCamera(cameraRepository);
        typicalJourneyTimeService = new TrafficDataServiceTypicalJourneyTime(typicalJourneyTimeRepository);
        pointDataService = new TrafficPointDataService(pointDataRepository, commentRepository);

        dataPersistence = new TrafficDataPersistence(incidentService, eventService, accidentService,
                                                     roadworkService, journeyTimeService, cameraService, typicalJourneyTimeService,
                                                     pointDataService, dataAccessService);

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

        when(typicalJourneyTimeRepository.findAll())
                .thenReturn(List.of(MockData.getTypicalJourneyTime("code1"),
                                    MockData.getTypicalJourneyTime("code2")));

        when(routingService.calculateRoute(any(Point.class), any(Point.class)))
                .thenReturn(MockData.getSimpleRoute());

    }

    @Test
    void listAllIncidents() {
        testListAll(TrafficDataTypes.INCIDENT, TrafficIncident.class);
    }

    @Test
    void listAllEvents() {
        testListAll(TrafficDataTypes.EVENT, TrafficEvent.class);
    }

    @Test
    void listAllAccidents() {
        testListAll(TrafficDataTypes.ACCIDENT, TrafficAccident.class);
    }

    @Test
    void listAllRoadworks() {
        testListAll(TrafficDataTypes.ROADWORKS, TrafficRoadwork.class);
    }

    @Test
    void listAllJourneyTimes() {
        testListAll(TrafficDataTypes.SPEED, JourneyTime.class);
    }

    @Test
    void listAllCamera() {
        testListAll(TrafficDataTypes.CAMERA, Camera.class);
    }

    @Test
    void listAllTypicalJourneyTimes() {
        testListAll(TrafficDataTypes.TYPICAL_SPEED, TypicalJourneyTime.class);
    }

    // Ensure that the routing service is being used to set a route for journeytime entities without existing
    // route information.
    @Test
    void persistJourneyTimeEntities() {
        List<TrafficEntity> journeyTimes = List.of(MockData.getJourneyTime("code1"),
                                                 MockData.getJourneyTime("code2"));

        dataPersistence.persistEntities(journeyTimes, TrafficDataTypes.SPEED);

        verify(journeyTimeRepository, times(1)).saveAll(journeyTimeCaptor.capture());

        List<JourneyTime> capturedJourneyTimes = journeyTimeCaptor.getValue();
        assertThat(capturedJourneyTimes.size()).isEqualTo(2);
        assertThat(capturedJourneyTimes.get(0).getRoute()).isNotNull();
    }

    @Test
    void getImage() {
        Camera mockCamera = MockData.getCamera("code1");
        when(cameraRepository.findById("code1"))
                .thenReturn(Optional.of(mockCamera));

        dataPersistence.getImage("code1");
        ArgumentCaptor<URL> urlCaptor = ArgumentCaptor.forClass(URL.class);
        verify(dataAccessService, times(1)).getImage(urlCaptor.capture());

        assertThat(urlCaptor.getValue()).isEqualTo(mockCamera.getImage());
    }

    @Test
    void findByCodeNumber() throws DataNotFoundException {
        String code = "code1";
        TrafficPointData mockData = MockData.getIncident(code);
        when(pointDataRepository.findById(code))
                .thenReturn(Optional.of(mockData));

        TrafficIncident incident = dataPersistence.find(code);

        assertThat(incident).isNotNull();
    }

    @Test
    void persistJourneytimesFromFile() throws IOException {
        ExternalDataAccessService externalDataAccessService = new ExternalDataAccessServiceImpl(new OpenDataServiceClient(
                new RestTemplateBuilder()));
        externalDataAccessService.setBaseDirectory("src/test/resources/data");

        List<JourneyTime> journeyTimes = externalDataAccessService.getData(TrafficDataTypes.SPEED, "journeytime-static-full-test.json", "journeytime-dynamic-full-test.json");

        dataPersistence.persistEntities(journeyTimes, TrafficDataTypes.SPEED);

        verify(journeyTimeRepository, times(1)).saveAll(journeyTimeCaptor.capture());

        List<JourneyTime> capturedJourneyTimes = journeyTimeCaptor.getValue();
        assertThat(capturedJourneyTimes.size()).isGreaterThan(0);
        assertThat(capturedJourneyTimes.get(0).getRoute()).isNotNull();
        System.out.println(capturedJourneyTimes.get(0));
    }

    private void testListAll(TrafficDataTypes dataType, Class<? extends TrafficEntity> expectedClass) {
        List<TrafficEntity> trafficData = dataPersistence.listAll(dataType);
        assertThat(trafficData).isNotNull();
        assertThat(trafficData.size()).isEqualTo(2);
        assertThat(trafficData.get(0)).isInstanceOf(expectedClass);
    }




}