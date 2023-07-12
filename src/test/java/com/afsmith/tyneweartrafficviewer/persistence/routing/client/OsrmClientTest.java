package com.afsmith.tyneweartrafficviewer.persistence.routing.client;

import com.afsmith.tyneweartrafficviewer.persistence.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.GeoJsonRouteExternal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(OsrmClient.class)
class OsrmClientTest {

    MockRestServiceServer server;

    ObjectMapper mapper = new ObjectMapper();

    OsrmClient client;

    GeoJsonPoint start = new GeoJsonPoint(54.9101791759364,-1.58476070926927);
    GeoJsonPoint end = new GeoJsonPoint(54.9150785396271, -1.58847047258996);

    @BeforeEach
    void setUp() {
        client = new OsrmClient(mapper);
        server = MockRestServiceServer.createServer(client.getRestTemplate());


    }

    @Test
    void getRouteResponse() {
        server.expect(method(HttpMethod.GET))
              .andExpect(requestTo(stringContainsInOrder(Double.toString(start.getLongitude()),
                                                         Double.toString(start.getLatitude()),
                                                         Double.toString(end.getLongitude()),
                                                         Double.toString(end.getLatitude()))))
              .andExpect(queryParam("geometries", "geojson"))
              .andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));

        String body = client.getRouteResponse(start, end);

        assertThat(body).isNotNull();

        server.verify();
    }

    @Test
    void getRoute() {
        server.expect(method(HttpMethod.GET))
              .andExpect(requestTo(stringContainsInOrder(Double.toString(start.getLongitude()),
                                                         Double.toString(start.getLatitude()),
                                                         Double.toString(end.getLongitude()),
                                                         Double.toString(end.getLatitude()))))
              .andExpect(queryParam("geometries", "geojson"))
              .andRespond(withSuccess(getExampleResponseBody(), MediaType.APPLICATION_JSON));


        GeoJsonRouteExternal route = client.getRoute(start, end);

        assertThat(route).isNotNull();
        assertThat(route.getRoutes().size()).isEqualTo(1);
        assertThat(route.getCode()).isEqualTo("Ok");
        assertThat(route.getWaypoints().size()).isEqualTo(2);

        server.verify();
    }

    @Test
    void constructUrl() {
        var start = new GeoJsonPoint(54.9101791759364,-1.58476070926927);
        var end = new GeoJsonPoint(54.9150785396271, -1.58847047258996);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl("http://localhost:5000/route/v1/driving/")
                                                .pathSegment(String.format("%s,%s;%s,%s", start.getLongitudeString(),
                                                                           start.getLatitudeString(),
                                                                           end.getLongitudeString(),
                                                                           end.getLatitudeString()))
                                                .queryParam("geometries", "geojson")
                                                .build();

        System.out.println(uri.toUri());

    }

    private String getExampleResponseBody() {
        return """
                {
                    "code": "Ok",
                    "routes": [
                        {
                            "geometry": {
                                "coordinates": [
                                    [
                                        -1.58476,
                                        54.910179
                                    ],
                                    [
                                        -1.58487,
                                        54.910685
                                    ],
                                    [
                                        -1.585003,
                                        54.910853
                                    ],
                                    [
                                        -1.585313,
                                        54.91104
                                    ],
                                    [
                                        -1.585438,
                                        54.911227
                                    ],
                                    [
                                        -1.585446,
                                        54.911427
                                    ],
                                    [
                                        -1.585204,
                                        54.911739
                                    ],
                                    [
                                        -1.58521,
                                        54.911989
                                    ],
                                    [
                                        -1.585424,
                                        54.912169
                                    ],
                                    [
                                        -1.586626,
                                        54.912857
                                    ],
                                    [
                                        -1.587339,
                                        54.913425
                                    ],
                                    [
                                        -1.587803,
                                        54.913913
                                    ],
                                    [
                                        -1.588222,
                                        54.914518
                                    ],
                                    [
                                        -1.588477,
                                        54.915078
                                    ]
                                ],
                                "type": "LineString"
                            },
                            "legs": [
                                {
                                    "steps": [],
                                    "summary": "",
                                    "weight": 39.7,
                                    "duration": 39.7,
                                    "distance": 627.7
                                }
                            ],
                            "weight_name": "routability",
                            "weight": 39.7,
                            "duration": 39.7,
                            "distance": 627.7
                        }
                    ],
                    "waypoints": [
                        {
                            "hint": "iNmFgP___38BAAAABAAAAEwAAAAKAAAACe9BQEBR_0Crs0BD8UHRQQEAAAAEAAAATAAAAAoAAACL_AAAiNHn_-PcRQOH0ef_49xFAwcADxZRglG3",
                            "distance": 0.064113442,
                            "name": "Newcastle Bank",
                            "location": [
                                -1.58476,
                                54.910179
                            ]
                        },
                        {
                            "hint": "HK2wh____38BAAAABgAAADIAAAAAAAAA1vknQNzHtkA3CJBCAAAAAAEAAAAGAAAAMgAAAAAAAACL_AAAA8Pn_wbwRQMKw-f_B_BFAwMAvxVRglG3",
                            "distance": 0.462394595,
                            "name": "Durham Road",
                            "location": [
                                -1.588477,
                                54.915078
                            ]
                        }
                    ]
                }
                """;
    }
}