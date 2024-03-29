package com.afsmith.tyneweartrafficviewer.persistence.external.client;

import com.afsmith.tyneweartrafficviewer.entities.AbstractTrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.config.RestTemplateBuilderConfig;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.ExternalDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficIncidentExternal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the client for fetching data from the North East open data service API.
 */
@RestClientTest(OpenDataServiceClient.class)
@Import(RestTemplateBuilderConfig.class)
class OpenDataServiceClientTest {

    OpenDataServiceClient client;
    MockRestServiceServer server;

    @Autowired
    RestTemplateBuilder restTemplateBuilderConfigured;

    @Mock
    RestTemplateBuilder mockRestTemplateBuilder = new RestTemplateBuilder(new MockServerRestTemplateCustomizer());

    @BeforeEach
    void setUp() {
        // Set up the mock server
        RestTemplate restTemplate = restTemplateBuilderConfigured.build();
        server = MockRestServiceServer.createServer(restTemplate);

        // Inject mock rest template into the client
        when(mockRestTemplateBuilder.build()).thenReturn(restTemplate);
        client = new OpenDataServiceClient(mockRestTemplateBuilder);
    }

    @Test
    void getIncidentData() throws Exception {
        String expectedUrl = "https://www.netraveldata.co.uk/api/v2" + ExternalDataTypes.INCIDENT.getUrl();
        String username = System.getenv("UTMCODS_USERNAME");
        String password = System.getenv("UTMCODS_PASSWORD");
        String authHeaderUnencoded =  username + ":" + password;
        String authHeaderEncoded = Base64.getEncoder().encodeToString(authHeaderUnencoded.getBytes());
        String responseBody = getIncidentJson();

        // Mock server expects to receive a correctly formatted HTTP GET request.
        server.expect(method(HttpMethod.GET))
                .andExpect(requestTo(expectedUrl))
                .andExpect(header("Authorization", "Basic " + authHeaderEncoded))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        List<TrafficDataExternal<AbstractTrafficData>> incidents = client.getData(ExternalDataTypes.INCIDENT);
        assertThat(incidents.size()).isEqualTo(1);
        assertThat(incidents.get(0)).isInstanceOf(TrafficIncidentExternal.class);
    }

    private String getIncidentJson() {
        return """
                [{
                     "systemCodeNumber": "Tyneandwear0000792812",
                     "type": "Incident",
                     "incidentTypeDescription": "BRIDGE CLOSED",
                     "shortDescription": "Salters bridge closed to motoris",
                     "longDescription": "Salters bridge closed to motorised traffic under a Experimental Traffic Regulations Order. More information at https://safenewcastlebridges.commonplace.is/",
                     "point": {
                       "easting": 425479,
                       "northing": 568542,
                       "latitude": 55.0107869827092000,
                       "longitude": -1.6031330330235400
                     },
                     "locationDescription": "Salters Brg",
                     "creationDate": "2020-08-20T09:56:49.733+0100",
                     "dataSourceTypeRef": "UTMC",
                     "confirmedDate": "2020-08-13T00:00:00.000+0100",
                     "modifiedDate": "2021-08-09T12:37:51.983+0100",
                     "severityTypeRefDescription": "Low",
                     "lanesAffectedTypeRefDescription": "T T",
                     "diversionInForce": "N",
                     "phaseTypeRef": "Current",
                     "incidentTime": "2020-08-13T06:00:35.857+0100",
                     "endTime": "2023-08-13T16:00:35.857+0100"
               }]
                """;
    }
}