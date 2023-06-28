package com.afsmith.tyneweartrafficviewer.util;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.entities.*;

import java.time.ZonedDateTime;

public class MockData {
    private static final ZonedDateTime time = ZonedDateTime.now();
    private static final PointDTO pointDTO = new PointDTO(1L, 1L, 0.0, 0.0);
    private static final Point point = new Point(1L, 1L, 0.0, 0.0);
    private static final PlannedTimes planned = new PlannedTimes(time, time);
    private static final PlannedDTO plannedDto = new PlannedDTO(time, time);


    // Get mock DTOs
    public static TrafficAccidentDTO getAccidentDto(String code) {
        return TrafficAccidentDTO.builder()
                                 .systemCodeNumber(code)
                                 .type(TrafficDataTypes.ACCIDENT)
                                 .shortDescription("short description")
                                 .longDescription("long description")
                                 .locationDescription("location description")
                                 .point(pointDTO)
                                 .creationDate(time)
                                 .dataSourceTypeRef("type ref")
                                 .confirmedDate(time)
                                 .modifiedDate(time)
                                 .severityTypeRefDescription("Low")
                                 .lanesAffectedTypeRefDescription("| T")
                                 .diversionInForce("N")
                                 .phaseTypeRef("current")
                                 .accidentTypeDescription("")
                                 .accidentTime(time)
                                 .endTime(time)
                                 .build();
    }

    public static TrafficRoadworksDTO getRoadworkDto(String code) {
        return TrafficRoadworksDTO.builder()
                                  .systemCodeNumber(code)
                                  .type(TrafficDataTypes.ROADWORKS)
                                  .shortDescription("short description")
                                  .longDescription("long description")
                                  .locationDescription("location description")
                                  .point(pointDTO)
                                  .creationDate(time)
                                  .dataSourceTypeRef("type ref")
                                  .confirmedDate(time)
                                  .modifiedDate(time)
                                  .severityTypeRefDescription("Low")
                                  .lanesAffectedTypeRefDescription("| T")
                                  .diversionInForce("N")
                                  .phaseTypeRef("current")
                                  .roadworkTypeDescription("")
                                  .contractor("contractor")
                                  .contraflow("N")
                                  .trafficSignals("N")
                                  .planned(plannedDto)
                                  .actual(plannedDto)
                                  .build();
    }

    public static JourneyTimeDTO getJourneyTimeDto(String code) {
        return JourneyTimeDTO.builder()
                             .systemCodeNumber(code)
                             .shortDescription("short description")
                             .longDescription("long description")
                             .point(pointDTO)
                             .endPoint(pointDTO)
                             .lastUpdated(time)
                             .linkTravelTime(100)
                             .platesIn(10)
                             .platesOut(10)
                             .plateMatches(10)
                             .lastUpdatedDynamic(time)
                             .build();
    }

    // Get Mock entities

    public static TrafficIncident getIncident(String code) {
        return TrafficIncident.builder()
                              .systemCodeNumber(code)
                              .type(TrafficDataTypes.ACCIDENT)
                              .shortDescription("short description")
                              .longDescription("long description")
                              .locationDescription("location description")
                              .point(point)
                              .creationDate(time)
                              .dataSourceTypeRef("type ref")
                              .confirmedDate(time)
                              .modifiedDate(time)
                              .severityTypeRefDescription("Low")
                              .lanesAffectedTypeRefDescription("| T")
                              .diversionInForce("N")
                              .phaseTypeRef("current")
                              .incidentTypeDescription("")
                              .incidentTime(time)
                              .endTime(time)
                              .build();
    }

    public static TrafficEvent getEvent(String code) {
        return TrafficEvent.builder()
                           .systemCodeNumber(code)
                           .type(TrafficDataTypes.ACCIDENT)
                           .shortDescription("short description")
                           .longDescription("long description")
                           .locationDescription("location description")
                           .point(point)
                           .creationDate(time)
                           .dataSourceTypeRef("type ref")
                           .confirmedDate(time)
                           .modifiedDate(time)
                           .severityTypeRefDescription("Low")
                           .lanesAffectedTypeRefDescription("| T")
                           .diversionInForce("N")
                           .phaseTypeRef("current")
                           .eventTypeDescription("")
                           .organiser("An organiser")
                           .venueName("venue")
                           .planned(planned)
                           .build();
    }

    public static TrafficAccident getAccident(String code) {
        return TrafficAccident.builder()
                                 .systemCodeNumber(code)
                                 .type(TrafficDataTypes.ACCIDENT)
                                 .shortDescription("short description")
                                 .longDescription("long description")
                                 .locationDescription("location description")
                                 .point(point)
                                 .creationDate(time)
                                 .dataSourceTypeRef("type ref")
                                 .confirmedDate(time)
                                 .modifiedDate(time)
                                 .severityTypeRefDescription("Low")
                                 .lanesAffectedTypeRefDescription("| T")
                                 .diversionInForce("N")
                                 .phaseTypeRef("current")
                                 .accidentTypeDescription("")
                                 .accidentTime(time)
                                 .endTime(time)
                                 .build();
    }

    public static TrafficRoadwork getRoadwork(String code) {
        return TrafficRoadwork.builder()
                                  .systemCodeNumber(code)
                                  .type(TrafficDataTypes.ROADWORKS)
                                  .shortDescription("short description")
                                  .longDescription("long description")
                                  .locationDescription("location description")
                                  .point(point)
                                  .creationDate(time)
                                  .dataSourceTypeRef("type ref")
                                  .confirmedDate(time)
                                  .modifiedDate(time)
                                  .severityTypeRefDescription("Low")
                                  .lanesAffectedTypeRefDescription("| T")
                                  .diversionInForce("N")
                                  .phaseTypeRef("current")
                                  .roadworkTypeDescription("")
                                  .contractor("contractor")
                                  .contraflow("N")
                                  .trafficSignals("N")
                                  .planned(planned)
                                  .actual(planned)
                                  .build();
    }

    public static JourneyTime getJourneyTime(String code) {
        return JourneyTime.builder()
                          .systemCodeNumber(code)
                          .shortDescription("short description")
                          .longDescription("long description")
                          .point(point)
                          .endPoint(point)
                          .lastUpdated(time)
                          .linkTravelTime(100)
                          .platesIn(10)
                          .platesOut(10)
                          .plateMatches(10)
                          .lastUpdatedDynamic(time)
                          .build();
    }
}
