package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.util.MockExternalData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test to verify that external data fetched by the Open Data Service
 * client is correctly returned as entities by the external data access service.
 */
@RestClientTest
class ExternalDataAccessServiceImplTest {
    ExternalDataAccessService dataAccessService;
    @Mock
    OpenDataServiceClient client;

    @BeforeEach
    void setUp() {
        dataAccessService = new ExternalDataAccessServiceImpl(client);
        dataAccessService.setBaseDirectory("src/test/resources/data");
    }

    @Test
    void getIncidentData() throws IOException {
        List<TrafficDataExternal<TrafficEntity>> mockData = List.of(
                buildIncident("incident1"),
                buildIncident("incident2"));
        when(client.getData(ExternalDataTypes.INCIDENT)).thenReturn(mockData);

        List<TrafficIncident> incidents = dataAccessService.getData(TrafficDataTypes.INCIDENT);
        assertThat(incidents.size()).isEqualTo(2);
    }

    @Test
    void getJourneyTimeData() throws IOException {
        List<TrafficDataExternal<TrafficEntity>> mockStatic = List.of(
                buildJourneyTimeStatic("code1"),
                buildJourneyTimeStatic("code2")
        );
        List<TrafficDataExternal<TrafficEntity>> mockDynamic = List.of(
                buildJourneyTimeDynamic("code1"),
                buildJourneyTimeDynamic("code2")
        );
        when(client.getData(ExternalDataTypes.JOURNEY_TIME_STATIC))
                .thenReturn(mockStatic);
        when(client.getData(ExternalDataTypes.JOURNEY_TIME_DYNAMIC))
                .thenReturn(mockDynamic);

        List<JourneyTime> journeyTimes = dataAccessService.getData(TrafficDataTypes.SPEED);
        assertThat(journeyTimes.size()).isEqualTo(2);
    }

    /**
     * When a static data type does not have a dynamic counterpart with a matching
     * system code number, an entity is constructed using the static data only.
     */
    @Test
    void getJourneyTimeDataMismatchedCodes() throws IOException {
        List<TrafficDataExternal<TrafficEntity>> mockStatic = List.of(
                buildJourneyTimeStatic("code1"),
                buildJourneyTimeStatic("code2")
        );
        List<TrafficDataExternal<TrafficEntity>> mockDynamic = List.of(
                buildJourneyTimeDynamic("code3"),
                buildJourneyTimeDynamic("code2")
        );
        when(client.getData(ExternalDataTypes.JOURNEY_TIME_STATIC))
                .thenReturn(mockStatic);
        when(client.getData(ExternalDataTypes.JOURNEY_TIME_DYNAMIC))
                .thenReturn(mockDynamic);

        List<JourneyTime> journeyTimes = dataAccessService.getData(TrafficDataTypes.SPEED);
        assertThat(journeyTimes.size()).isEqualTo(2);
        assertThat(journeyTimes.get(0).getLastUpdatedDynamic()).isNull();
    }

    @Test
    void getIncidentDataFromFile() throws IOException {
        List<TrafficIncident> incidents = dataAccessService.getData(TrafficDataTypes.INCIDENT, "incidents-test.json");

        assertThat(incidents.size()).isEqualTo(1);
        assertThat(incidents.get(0).getIncidentTypeDescription()).isEqualTo("BRIDGE CLOSED");
    }

    @Test
    void getJourneyTimeDataFromFile() throws IOException {
        List<JourneyTime> journeyTimes = dataAccessService.getData(TrafficDataTypes.SPEED, "journeytime-static-full-test.json", "journeytime-dynamic-full-test.json");

        assertThat(journeyTimes.size()).isGreaterThan(0);
        assertThat(journeyTimes.get(0).getRoute()).isNull();
    }

    /*
     * The following helper methods return mock data in the format returned by
     * the client. Unchecked casts are used to get the data into the appropriate
     * type but these are safe as the returned objects are all directly constructed
     * as subtypes of the returned type.
     */
    @SuppressWarnings("unchecked")
    private <E extends TrafficEntity> TrafficDataExternal<E> buildIncident(String code) {
        TrafficIncidentExternal incident = TrafficIncidentExternal.builder()
                                                                  .systemCodeNumber(code)
                                                                  .build();
        return (TrafficDataExternal<E>) incident;
    }

    @SuppressWarnings("unchecked")
    private <E extends TrafficEntity> TrafficDataExternal<E> buildJourneyTimeStatic(String code) {
        JourneytimeStaticExternal journeyTime = MockExternalData.getJourneyTimeStaticExternal(code);
        return (TrafficDataExternal<E>) journeyTime;
    }

    @SuppressWarnings("unchecked")
    private <E extends TrafficEntity> TrafficDataExternal<E> buildJourneyTimeDynamic(String code) {
        JourneytimeDynamicExternal journeyTime = MockExternalData.getJourneyTimeDynamicExternal(code);
        return (TrafficDataExternal<E>) journeyTime;
    }

    private <T extends TrafficDataExternal<E>, E extends TrafficEntity> TrafficDataExternal<E> getExternalData(T data) {
        return data;
    }

}