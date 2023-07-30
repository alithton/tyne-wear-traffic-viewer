package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.time.ZonedDateTime;

/**
 * A collection of methods for mapping {@link com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO}
 * to the relevant traffic entities. The type of entity to map to is inferred from the TrafficDataType.
 */
public class NewTrafficDataMapper {

    public static TrafficDataConverter convert(NewTrafficDataDTO newData) {
        return new TrafficDataConverter(newData, toPointData(newData));
    }


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

    public static class TrafficDataConverter {
        private final NewTrafficDataDTO newData;
        private final TrafficPointData pointData;

        private TrafficDataConverter(NewTrafficDataDTO newData, TrafficPointData pointData) {
            this.newData = newData;
            this.pointData = pointData;
        }

        public TrafficPointData toPointData() {
            return pointData;
        }

        public TrafficIncident toIncident() {
            TrafficIncident incident = new TrafficIncident(pointData);
            incident.setIncidentTime(newData.getStart());
            incident.setEndTime(newData.getEnd());
            incident.setIncidentTypeDescription("Custom Incident");
            return incident;
        }

        public TrafficEvent toEvent() {
            TrafficEvent event = new TrafficEvent(pointData);
            event.setPlanned(newData.getTimes());
            event.setEventTypeDescription("Custom Event");
            return event;
        }

        public TrafficRoadwork toRoadwork() {
            TrafficRoadwork roadwork = new TrafficRoadwork(pointData);
            roadwork.setActual(newData.getTimes());
            roadwork.setRoadworkTypeDescription("Custom Roadwork");
            return roadwork;
        }

        public TrafficAccident toAccident() {
            TrafficAccident accident = new TrafficAccident(pointData);
            accident.setAccidentTime(newData.getStart());
            accident.setEndTime(newData.getEnd());
            accident.setAccidentTypeDescription("Custom Accident");
            return accident;
        }

    }
}
