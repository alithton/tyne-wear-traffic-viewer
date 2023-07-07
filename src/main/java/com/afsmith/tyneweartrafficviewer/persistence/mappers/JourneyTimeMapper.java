package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.GeoJsonPointDTO;
import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JourneyTimeMapper extends TrafficDataMapper<JourneyTimeDTO, JourneyTime>{

    @Mapping(source = "entity", target = "averageSpeed")
    JourneyTimeDTO entityToDto(JourneyTime entity);

    default GeoJsonPoint map(GeoJsonPointDTO value) {
        return new GeoJsonPoint(value.getLatitude(), value.getLongitude());
    }

    default GeoJsonPointDTO map(GeoJsonPoint value) {
        return new GeoJsonPointDTO(value.latitude(), value.longitude());
    }

    /**
     * Calculate the average speed in miles per hour. If either the route distance data
     * or the travel time data are invalid or missing, returns 0.
     * @param source The JPA entity from which the data is sourced.
     * @return Average speed in miles per hour, or 0 if the data is invalid.
     */
    default double mapAverageSpeed(JourneyTime source) {
        if (source.getRoute() == null || source.getLinkTravelTime() == 0) return 0.0;
        double distanceMetres = source.getRoute().getDistance();
        double timeSeconds = source.getLinkTravelTime();
        double speedKmh = (distanceMetres / timeSeconds) * 3.6;
        return speedKmh * 0.621371;
    }
}
