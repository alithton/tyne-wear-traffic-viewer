package com.afsmith.tyneweartrafficviewer.persistence.routing.services;

import com.afsmith.tyneweartrafficviewer.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.routing.client.OsrmClient;
import com.afsmith.tyneweartrafficviewer.persistence.routing.mappers.OsrmToSimpleRouteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoutingServiceTest {

    RoutingService routingService;

    @Mock
    OsrmClient client;
    OsrmToSimpleRouteMapper mapper = Mappers.getMapper(OsrmToSimpleRouteMapper.class);

    @BeforeEach
    void setUp() {
        routingService = new RoutingService(client, mapper);
    }

    @Test
    void readRoutes() {
        String routeJson = getRouteJson();
        InputStream routeStream = new ByteArrayInputStream(routeJson.getBytes());

        Map<String, SimpleRoute> routeMap = routingService.read(routeStream);

        assertThat(routeMap.size()).isEqualTo(2);
    }

    @Test
    void readRoutesFromFile() throws IOException {
        Path routeFilePath = Paths.get(System.getProperty("user.dir") + "/src/test/output/routes.json");
        Map<String, SimpleRoute> routeMap = routingService.read(Files.newInputStream(routeFilePath));

        assertThat(routeMap.size()).isEqualTo(2);
    }

    @Test
    void readReturnsEmptyMapOnException() {
        InputStream emptyStream = new ByteArrayInputStream("".getBytes());
        Map<String, SimpleRoute> routeMap = routingService.read(emptyStream);

        assertThat(routeMap).isEmpty();
    }

    private String getRouteJson() {
        return """
                {
                  "code2" : {
                    "coordinates" : [ [ 59.1, -1.0 ], [ 58.9, 1.2 ] ],
                    "distance" : 627.5,
                    "duration" : 59.6
                  },
                  "code1" : {
                    "coordinates" : [ [ 59.1, -1.0 ], [ 58.9, 1.2 ] ],
                    "distance" : 627.5,
                    "duration" : 59.6
                  }
                }""";
    }

}