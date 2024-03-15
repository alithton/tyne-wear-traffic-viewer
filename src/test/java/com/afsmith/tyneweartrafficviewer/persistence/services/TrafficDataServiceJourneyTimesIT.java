package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessServiceImpl;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TrafficDataRepository;
import com.afsmith.tyneweartrafficviewer.persistence.routing.client.OsrmClient;
import com.afsmith.tyneweartrafficviewer.persistence.routing.mappers.OsrmToSimpleRouteMapperImpl;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({RoutingService.class, OsrmToSimpleRouteMapperImpl.class, OsrmClient.class, ObjectMapper.class})
public class TrafficDataServiceJourneyTimesIT {

    TrafficDataServiceJourneyTimes journeyTimeService;

    @Autowired
    RoutingService routingService;

    @Autowired
    TrafficDataRepository repository;

    ExternalDataAccessService externalDataAccessService;

    @Captor
    ArgumentCaptor<List<JourneyTime>> captor;

    @BeforeEach
    void setUp() {
        journeyTimeService = new TrafficDataServiceJourneyTimes(repository, routingService);
        journeyTimeService.setRoutesFromFile(true);

        OpenDataServiceClient client = new OpenDataServiceClient(new RestTemplateBuilder());
        externalDataAccessService = new ExternalDataAccessServiceImpl(client);
        externalDataAccessService.setBaseDirectory("src/test/resources/data");
    }


    @Test
    void persistJourneyTimes() throws IOException {

        List<JourneyTime> journeyTimes = externalDataAccessService.getData(TrafficDataTypes.SPEED, "journeytime-static-full-test.json", "journeytime-dynamic-full-test.json");

        journeyTimeService.persistEntities(journeyTimes);

        assertThat(repository.count()).isGreaterThan(0L);
    }


}
