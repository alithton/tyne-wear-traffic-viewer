package com.afsmith.tyneweartrafficviewer.persistence.routing.mappers;

import com.afsmith.tyneweartrafficviewer.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPointExternal;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.OsrmRoute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface OsrmToSimpleRouteMapper {

    @Mapping(source="external", target="coordinates")
    @Mapping(source = "external", target="distance", qualifiedByName = "distance")
    @Mapping(source = "external", target="duration", qualifiedByName = "duration")
    SimpleRoute externalToEntity(OsrmRoute external);

    default List<GeoJsonPoint> mapCoordinates(OsrmRoute external) {
        List<GeoJsonPointExternal> externalCoords =  external.getRoutes().get(0)
                                                       .getGeometry()
                                                       .getCoordinates();
        return externalCoords.stream()
                             .map(point -> new GeoJsonPoint(point.getLatitude(), point.getLongitude()))
                             .toList();
    }

    @Named("distance")
    default double mapDistance(OsrmRoute external) {
        return external.getRoutes().get(0)
                .getDistance();
    }

    @Named("duration")
    default double mapDuration(OsrmRoute external) {
        return external.getRoutes().get(0)
                .getDuration();
    }
}
