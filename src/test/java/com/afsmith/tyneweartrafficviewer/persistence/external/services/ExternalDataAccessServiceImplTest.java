package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.external.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.ExternalDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficIncidentExternal;
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
    }

    @Test
    void getIncidentData() throws IOException {
        List<TrafficDataExternal<TrafficData>> mockData = List.of(
                buildIncident("incident1"),
                buildIncident("incident2"));
        when(client.getData(ExternalDataTypes.INCIDENT)).thenReturn(mockData);

        List<TrafficIncident> incidents = dataAccessService.getData(TrafficDataTypes.INCIDENT);
        assertThat(incidents.size()).isEqualTo(2);
    }

    @SuppressWarnings("unchecked")
    private <E extends TrafficData> TrafficDataExternal<E> buildIncident(String code) {
        TrafficIncidentExternal incident = TrafficIncidentExternal.builder()
                                                                  .systemCodeNumber(code)
                                                                  .build();
        return (TrafficDataExternal<E>) incident;
    }

}