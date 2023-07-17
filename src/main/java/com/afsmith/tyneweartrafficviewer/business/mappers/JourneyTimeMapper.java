package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.GeoJsonPointDTO;
import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JourneyTimeMapper extends TrafficDataMapper<JourneyTimeDTO, JourneyTime>{

    @Mapping(source = "entity", target = "averageSpeed")
    JourneyTimeDTO entityToDto(JourneyTime entity);

    @Mapping(target = "averageSpeed", expression = "java( mapAverageSpeed(journeyTime, typical) )")
    @Mapping(source = "journeyTime.systemCodeNumber", target = "systemCodeNumber")
    JourneyTimeDTO entityToDto(JourneyTime journeyTime, TypicalJourneyTime typical);

    default GeoJsonPoint map(GeoJsonPointDTO value) {
        return new GeoJsonPoint(value.getLatitude(), value.getLongitude());
    }

    default GeoJsonPointDTO map(GeoJsonPoint value) {
        return new GeoJsonPointDTO(value.getLatitude(), value.getLongitude());
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
        return calculateAverageSpeedMph(distanceMetres, timeSeconds);
    }

    default double mapAverageSpeed(JourneyTime journeyTime, TypicalJourneyTime typical) {
        if (journeyTime.getRoute() == null) return 0.0;
        double distance = journeyTime.getRoute().getDistance();
        double time = typical.getTravelTime();
        return calculateAverageSpeedMph(distance, time);
    }

    default double calculateAverageSpeedMph(double distance, double time) {
        double speedKmh = (distance / time) * 3.6;
        return speedKmh * 0.621371;
    }
}
