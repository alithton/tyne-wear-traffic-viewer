package com.afsmith.tyneweartrafficviewer.persistence.routing.services;

import com.afsmith.tyneweartrafficviewer.persistence.entities.Point;
import com.afsmith.tyneweartrafficviewer.persistence.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.routing.client.OsrmClient;
import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.mappers.GeoJsonExternalToSimpleRouteMapper;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.GeoJsonRouteExternal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
