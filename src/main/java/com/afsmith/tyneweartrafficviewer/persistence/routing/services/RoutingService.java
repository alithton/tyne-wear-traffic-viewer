package com.afsmith.tyneweartrafficviewer.persistence.routing.services;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.routing.client.OsrmClient;
import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.mappers.GeoJsonExternalToSimpleRouteMapper;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.GeoJsonRouteExternal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@Service
public class RoutingService {
    private final OsrmClient client;
    private final GeoJsonExternalToSimpleRouteMapper mapper;

    public SimpleRoute calculateRoute(Point start, Point end) {
        GeoJsonPoint startExternal = new GeoJsonPoint(start.getLatitude(), start.getLongitude());
        GeoJsonPoint endExternal = new GeoJsonPoint(end.getLatitude(), end.getLongitude());

        GeoJsonRouteExternal routeExternal = client.getRoute(startExternal, endExternal);
        return mapper.externalToEntity(routeExternal);
    }

    public Map<String, SimpleRoute> read(InputStream src) {
        ObjectMapper objectMapper = new ObjectMapper();
        MapType simpleRoutesListType = objectMapper.getTypeFactory()
                                                   .constructMapType(Map.class, String.class, SimpleRoute.class);
        Map<String, SimpleRoute> routeMap;
        try {
            routeMap = objectMapper.readValue(src, simpleRoutesListType);
        } catch (IOException e) {
            e.printStackTrace();
            routeMap = new HashMap<>();
        }
        return routeMap;
    }
}
