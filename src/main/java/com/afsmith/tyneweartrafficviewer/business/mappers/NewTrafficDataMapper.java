package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.time.ZonedDateTime;

/**
 * A collection of methods for mapping {@link com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO}
 * to the relevant traffic entities.
 */
public class NewTrafficDataMapper {

    /**
     * Use the provided new traffic data to create a traffic data converter instance,
     * which provides an API for converting the traffic data to a concrete traffic
     * data type.
     * @param newData The data for the new traffic incident.
     * @return A traffic data converter instance.
     */
    public static TrafficDataConverter convert(NewTrafficDataDTO newData) {
        return new TrafficDataConverter(newData, toPointData(newData));
    }

    /*
     * Use the provided traffic data to construct an instance of the generic traffic
     * point data class.
     */
    private static TrafficPointData toPointData(NewTrafficDataDTO newData) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        Point newPoint = new Point(newData.getLatitude(), newData.getLongitude());
        String typeRef = "User";
        String systemCodeNumber = generateSystemCodeNumber(newData);

        TrafficPointData pointData = new TrafficPointData();
        pointData.setSystemCodeNumber(systemCodeNumber);
        pointData.setType(newData.getType());
        pointData.setShortDescription(newData.getShortDescription());
        pointData.setLongDescription(newData.getLongDescription());
        pointData.setLocationDescription(newData.getLocationDescription());
        pointData.setPoint(newPoint);
        pointData.setCreationDate(currentTime);
        pointData.setDataSourceTypeRef(typeRef);
        pointData.setConfirmedDate(currentTime);
        pointData.setModifiedDate(currentTime);
        pointData.setSeverityTypeRefDescription(newData.getSeverityTypeRefDescription());
        pointData.setLanesAffectedTypeRefDescription(newData.getLanesAffectedTypeRefDescription());
        pointData.setDiversionInForce(newData.getDiversionInForce());
        pointData.setPhaseTypeRef(newData.getPhaseTypeRef());

        return pointData;
    }

    // A simple method for generating a unique code using the hash code.
    private static String generateSystemCodeNumber(NewTrafficDataDTO newData) {
        return "Custom" + newData.hashCode();
    }

    /**
     * Inner class providing a convenient API for conversion of data for new traffic
     * incidents into a concrete traffic data type.
     */
    public static class TrafficDataConverter {
        private final NewTrafficDataDTO newData;
        private final TrafficPointData pointData;

        /*
         * Private constructor for the data converter.
         */
        private TrafficDataConverter(NewTrafficDataDTO newData, TrafficPointData pointData) {
            this.newData = newData;
            this.pointData = pointData;
        }

        /**
         * Convert the entered data into a generic traffic point data instance.
         */
        public TrafficPointData toPointData() {
            return pointData;
        }

        /**
         * Convert the entered data to a traffic incident.
         * @return The constructed incident data.
         */
        public TrafficIncident toIncident() {
            TrafficIncident incident = new TrafficIncident(pointData);
            incident.setIncidentTime(newData.getStart());
            incident.setEndTime(newData.getEnd());
            incident.setIncidentTypeDescription("Custom Incident");
            return incident;
        }

        /**
         * Convert the entered data to a traffic event.
         * @return The constructed event data.
         */
        public TrafficEvent toEvent() {
            TrafficEvent event = new TrafficEvent(pointData);
            event.setPlanned(newData.getTimes());
            event.setEventTypeDescription("Custom Event");
            return event;
        }

        /**
         * Convert the entered data to a roadwork.
         * @return The constructed roadwork data.
         */
        public TrafficRoadwork toRoadwork() {
            TrafficRoadwork roadwork = new TrafficRoadwork(pointData);
            roadwork.setActual(newData.getTimes());
            roadwork.setRoadworkTypeDescription("Custom Roadwork");
            return roadwork;
        }

        /**
         * Convert the entered data to a traffic accident.
         * @return The constructed accident data.
         */
        public TrafficAccident toAccident() {
            TrafficAccident accident = new TrafficAccident(pointData);
            accident.setAccidentTime(newData.getStart());
            accident.setEndTime(newData.getEnd());
            accident.setAccidentTypeDescription("Custom Accident");
            return accident;
        }

    }
}
