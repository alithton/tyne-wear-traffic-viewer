package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TrafficDataRepository;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TrafficDataServiceJourneyTimesTest {
    TrafficDataServiceJourneyTimes journeyTimeService;

    @Mock
    RoutingService routingService;

    @Mock
    TrafficDataRepository repository;

    List<JourneyTime> mockJourneyTimes;

    @Captor
    ArgumentCaptor<List<JourneyTime>> captor;

    @BeforeEach
    void setUp() {

        journeyTimeService = new TrafficDataServiceJourneyTimes(repository, routingService);
        journeyTimeService.setRouteOutputFile("/src/test/output/routes.json");

        mockJourneyTimes = List.of(MockData.getJourneyTime("code1"),
                                   MockData.getJourneyTime("code2"));

    }

    @Test
    void routeDataWrittenToFile() {
        when(routingService.calculateRoute(any(Point.class), any(Point.class)))
                .thenReturn(MockData.getSimpleRoute());

        journeyTimeService.persistEntities(mockJourneyTimes);
    }

    @Test
    void routesReadFromFile() {
        when(routingService.read(any(InputStream.class)))
                .thenReturn(Map.of("code1", MockData.getSimpleRoute(),
                                   "code2", MockData.getSimpleRoute()));

        journeyTimeService.setRoutesFromFile(true);
        journeyTimeService.persistEntities(mockJourneyTimes);

        verify(repository).saveAll(captor.capture());

        JourneyTime firstJourney = captor.getValue()
                                         .get(0);

        assertThat(firstJourney.getRoute()).isNotNull();
    }

}