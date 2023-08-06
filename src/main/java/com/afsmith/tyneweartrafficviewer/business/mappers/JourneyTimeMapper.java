package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.ComparisonDTO;
import com.afsmith.tyneweartrafficviewer.business.data.GeoJsonPointDTO;
import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Defines a mapping from journey time entities to DTOs.
 */
@Mapper
public interface JourneyTimeMapper extends TrafficDataMapper<JourneyTimeDTO, JourneyTime>{

    /**
     * Given a journey time entity, get the corresponding DTO, containing current
     * journey time data.
     * @param entity The entity.
     * @return A journey time data transfer object.
     */
    @Mapping(source = "entity", target = "speed")
    JourneyTimeDTO entityToDto(JourneyTime entity);

    /**
     * Given a journey time entity and typical journey time data (which are assumed
     * to be for the same stretch of road), get a journey time DTO containing
     * typical journey time data.
     * @param journeyTime The journey time data.
     * @param typical The typical journey time data.
     * @return A data transfer object containing typical journey time data.
     */
    @Mapping(target = "speed", expression = "java( mapAverageSpeed(journeyTime, typical) )")
    @Mapping(source = "journeyTime.systemCodeNumber", target = "systemCodeNumber")
    JourneyTimeDTO entityToDto(JourneyTime journeyTime, TypicalJourneyTime typical);

    /**
     * Given a journey time entity and typical journey time data (which are assumed
     * to be for the same stretch of road), get a comparison DTO representing
     * a comparison of current journey times to typical journey times.
     * @param journeyTime The journey time data.
     * @param typical The typical journey time data.
     * @return A data transfer object containing the comparison data.
     */
    @Mapping(source = "journeyTime", target = "speed")
    @Mapping(target = "typicalSpeed", expression = "java( mapAverageSpeed(journeyTime, typical) )")
    @Mapping(source = "journeyTime.systemCodeNumber", target = "systemCodeNumber")
    ComparisonDTO comparison(JourneyTime journeyTime, TypicalJourneyTime typical);

    /**
     * Given the provided point, get the corresponding point DTO.
     * @param value The point data.
     * @return A point data transfer object.
     */
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

    /**
     * Calculate the typical average speed in miles per hour. If the route data
     * is missing, returns 0.
     * @param journeyTime The journey time data.
     * @param typical The typical journey time data.
     * @return Average speed in miles per hour, or 0 if the data is invalid.
     */
    default double mapAverageSpeed(JourneyTime journeyTime, TypicalJourneyTime typical) {
        if (journeyTime.getRoute() == null) return 0.0;
        double distance = journeyTime.getRoute().getDistance();
        double time = typical.getTravelTime();
        return calculateAverageSpeedMph(distance, time);
    }

    /**
     * Calculate the speed in miles per hour.
     * @param distance The distance in metres.
     * @param time The time in seconds.
     * @return The speed in miles per hour.
     */
    default double calculateAverageSpeedMph(double distance, double time) {
        double speedKmh = (distance / time) * 3.6;
        return speedKmh * 0.621371;
    }
}
