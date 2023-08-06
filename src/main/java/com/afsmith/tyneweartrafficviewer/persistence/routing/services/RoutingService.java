package com.afsmith.tyneweartrafficviewer.persistence.routing.services;

import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.routing.client.OsrmClient;
import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.mappers.OsrmToSimpleRouteMapper;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.OsrmRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A service that provides route data, either by dynamically generating it using
 * an external service or by reading it from an input.
 */
@Getter
@RequiredArgsConstructor
@Service
public class RoutingService {
    private final OsrmClient client;
    private final OsrmToSimpleRouteMapper mapper;

    /**
     * Dynamically generate a route provided start and end points using the Open
     * Source Routing Machine service.
     * @param start The starting coordinates.
     * @param end The ending coordinates.
     * @return The route data.
     */
    public SimpleRoute calculateRoute(Point start, Point end) {
        GeoJsonPoint startExternal = new GeoJsonPoint(start.getLatitude(), start.getLongitude());
        GeoJsonPoint endExternal = new GeoJsonPoint(end.getLatitude(), end.getLongitude());

        OsrmRoute routeExternal = client.getRoute(startExternal, endExternal);
        return mapper.externalToEntity(routeExternal);
    }

    /**
     * Read route data from an input stream (this would typically be data read in from a
     * file, for example). The source route data is expected to be in JSON format with
     * an identifier key.
     * @param src The route data input stream.
     * @return A map of some identifier value to the route data.
     */
    public Map<String, SimpleRoute> read(InputStream src) {
        ObjectMapper objectMapper = new ObjectMapper();
        MapType simpleRoutesListType = objectMapper.getTypeFactory()
                                                   .constructMapType(Map.class, String.class, SimpleRoute.class);
        Map<String, SimpleRoute> routeMap;
        try {
            routeMap = objectMapper.readValue(src, simpleRoutesListType);
        } catch (IOException e) {
            routeMap = new HashMap<>();
        }
        return routeMap;
    }
}
