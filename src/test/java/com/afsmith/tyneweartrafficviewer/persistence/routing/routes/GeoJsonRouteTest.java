package com.afsmith.tyneweartrafficviewer.persistence.routing.routes;

import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPointExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeoJsonRouteTest {
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void readPointJson() throws JsonProcessingException {
        String pointJson = getPointJson();
        GeoJsonPointExternal point = mapper.readValue(pointJson, GeoJsonPointExternal.class);

        assertThat(Double.toString(point.getLongitude())).isEqualTo("-1.58476");
        assertThat(Double.toString(point.getLatitude())).isEqualTo("54.910179");
    }

    @Test
    void readOsrmResponse() throws JsonProcessingException {
        String response = getExampleResponseBody();
        OsrmRoute route = mapper.readValue(response, OsrmRoute.class);

        assertThat(route).isNotNull();
        assertThat(route.getCode()).isEqualTo("Ok");
        assertThat(route.getRoutes().size()).isEqualTo(1);

        OsrmRoute.Route firstRoute = route.getRoutes().get(0);

        assertThat(firstRoute.getGeometry().getCoordinates().size()).isEqualTo(14);
        assertThat(firstRoute.getDistance()).isBetween(627.6, 627.8);
        assertThat(firstRoute.getDuration()).isBetween(39.6, 39.8);
    }

    private String getPointJson() {
        return """
                    [
                        -1.58476,
                        54.910179
                    ]""";
    }

    private String getCoordinatesJson() {
        return """
                {"coordinates": [
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
                                ]}""";
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