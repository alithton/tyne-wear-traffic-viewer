package com.afsmith.tyneweartrafficviewer.persistence.routing.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.GeoJsonRouteExternal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.assertj.core.api.Assertions.assertThat;

class GeoJsonExternalToSimpleRouteMapperTest {

    GeoJsonExternalToSimpleRouteMapper mapper = Mappers.getMapper(GeoJsonExternalToSimpleRouteMapper.class);

    @Test
    void externalToEntity() throws JsonProcessingException {

        GeoJsonRouteExternal external = getGeoJsonExternal();

        SimpleRoute route = mapper.externalToEntity(external);

        assertThat(route).isNotNull();
        assertThat(route.getCoordinates()).isNotNull();
        assertThat(route.getCoordinates().size()).isEqualTo(14);
        assertThat(route.getDuration()).isBetween(39.6, 39.8);
        assertThat(route.getDistance()).isBetween(627.6, 627.8);
    }

    private GeoJsonRouteExternal getGeoJsonExternal() throws JsonProcessingException {
        String json = """
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

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, GeoJsonRouteExternal.class);
    }
}