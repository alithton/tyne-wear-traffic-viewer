package com.afsmith.tyneweartrafficviewer.util;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public class MockData {
    private static final ZonedDateTime TIME = ZonedDateTime.now();
    private static final PointDTO POINT_DTO = new PointDTO(1L, 1L, 0.0, 0.0);
    private static final Point POINT = new Point(1L, 1L, 0.0, 0.0);
    private static final PlannedTimes PLANNED = new PlannedTimes(TIME, TIME);
    private static final PlannedDTO PLANNED_DTO = new PlannedDTO(TIME, TIME);
    public static final String SHORT_DESCRIPTION = "short description";
    public static final String LONG_DESCRIPTION = "long description";
    public static final String LOCATION_DESCRIPTION = "location description";
    public static final String TYPE_REF = "type ref";
    public static final String SEVERITY = "Low";
    public static final String LANES_AFFECTED = "| T";
    public static final String DIVERSION = "N";
    public static final String PHASE_TYPE = "current";
    public static final String TYPE_DESCRIPTION = "type description";
    public static final String IMAGE_URL_TEXT = "https://example.com/image.jpg";
    public static final URL IMAGE_URL = getImageUrl();

    private static URL getImageUrl() {
        URL imageUrl = null;
        try {
            imageUrl = new URL(IMAGE_URL_TEXT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }


    // Get mock DTOs
    public static TrafficIncidentDTO getIncidentDto(String code) {
        return TrafficIncidentDTO.builder()
                                 .systemCodeNumber(code)
                                 .type(TrafficDataTypes.INCIDENT)
                                 .shortDescription(SHORT_DESCRIPTION)
                                 .longDescription(LONG_DESCRIPTION)
                                 .locationDescription(LOCATION_DESCRIPTION)
                                 .incidentTime(TIME)
                                 .point(POINT_DTO)
                                 .confirmedDate(TIME)
                                 .endTime(TIME)
                                 .creationDate(TIME)
                                 .dataSourceTypeRef(TYPE_REF)
                                 .diversionInForce(DIVERSION)
                                 .incidentTypeDescription(TYPE_DESCRIPTION)
                                 .locationDescription(LANES_AFFECTED)
                                 .severityTypeRefDescription(SEVERITY)
                                 .modifiedDate(TIME)
                                 .phaseTypeRef(PHASE_TYPE)
                                 .build();
    }

    public static TrafficEventDTO getEventDto(String code) {
        return TrafficEventDTO.builder()
                              .systemCodeNumber(code)
                              .type(TrafficDataTypes.ACCIDENT)
                              .shortDescription("short description")
                              .longDescription("long description")
                              .locationDescription("location description")
                              .point(POINT_DTO)
                              .creationDate(TIME)
                              .dataSourceTypeRef("type ref")
                              .confirmedDate(TIME)
                              .modifiedDate(TIME)
                              .severityTypeRefDescription("Low")
                              .lanesAffectedTypeRefDescription("| T")
                              .diversionInForce("N")
                              .phaseTypeRef("current")
                              .eventTypeDescription("")
                              .organiser("An organiser")
                              .venueName("venue")
                              .planned(PLANNED_DTO)
                              .build();
    }


    public static TrafficAccidentDTO getAccidentDto(String code) {
        return TrafficAccidentDTO.builder()
                                 .systemCodeNumber(code)
                                 .type(TrafficDataTypes.ACCIDENT)
                                 .shortDescription("short description")
                                 .longDescription("long description")
                                 .locationDescription("location description")
                                 .point(POINT_DTO)
                                 .creationDate(TIME)
                                 .dataSourceTypeRef("type ref")
                                 .confirmedDate(TIME)
                                 .modifiedDate(TIME)
                                 .severityTypeRefDescription("Low")
                                 .lanesAffectedTypeRefDescription("| T")
                                 .diversionInForce("N")
                                 .phaseTypeRef("current")
                                 .accidentTypeDescription("")
                                 .accidentTime(TIME)
                                 .endTime(TIME)
                                 .build();
    }

    public static TrafficRoadworksDTO getRoadworkDto(String code) {
        return TrafficRoadworksDTO.builder()
                                  .systemCodeNumber(code)
                                  .type(TrafficDataTypes.ROADWORKS)
                                  .shortDescription("short description")
                                  .longDescription("long description")
                                  .locationDescription("location description")
                                  .point(POINT_DTO)
                                  .creationDate(TIME)
                                  .dataSourceTypeRef("type ref")
                                  .confirmedDate(TIME)
                                  .modifiedDate(TIME)
                                  .severityTypeRefDescription("Low")
                                  .lanesAffectedTypeRefDescription("| T")
                                  .diversionInForce("N")
                                  .phaseTypeRef("current")
                                  .roadworkTypeDescription("")
                                  .contractor("contractor")
                                  .contraflow("N")
                                  .trafficSignals("N")
                                  .planned(PLANNED_DTO)
                                  .actual(PLANNED_DTO)
                                  .build();
    }

    public static JourneyTimeDTO getJourneyTimeDto(String code) {
        return JourneyTimeDTO.builder()
                             .systemCodeNumber(code)
                             .shortDescription("short description")
                             .longDescription("long description")
                             .point(POINT_DTO)
                             .endPoint(POINT_DTO)
                             .lastUpdated(TIME)
                             .linkTravelTime(100)
                             .platesIn(10)
                             .platesOut(10)
                             .plateMatches(10)
                             .lastUpdatedDynamic(TIME)
                             .build();
    }

    public static CameraDTO getCameraDto(String code) {
        return CameraDTO.builder()
                        .systemCodeNumber(code)
                        .shortDescription(SHORT_DESCRIPTION)
                        .longDescription(LONG_DESCRIPTION)
                        .point(POINT_DTO)
                        .lastUpdated(TIME)
                        .lastUpdatedDynamic(TIME)
                        .image(IMAGE_URL)
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
                              .point(POINT)
                              .creationDate(TIME)
                              .dataSourceTypeRef("type ref")
                              .confirmedDate(TIME)
                              .modifiedDate(TIME)
                              .severityTypeRefDescription("Low")
                              .lanesAffectedTypeRefDescription("| T")
                              .diversionInForce("N")
                              .phaseTypeRef("current")
                              .incidentTypeDescription("")
                              .incidentTime(TIME)
                              .endTime(TIME)
                              .build();
    }

    public static TrafficEvent getEvent(String code) {
        return TrafficEvent.builder()
                           .systemCodeNumber(code)
                           .type(TrafficDataTypes.ACCIDENT)
                           .shortDescription("short description")
                           .longDescription("long description")
                           .locationDescription("location description")
                           .point(POINT)
                           .creationDate(TIME)
                           .dataSourceTypeRef("type ref")
                           .confirmedDate(TIME)
                           .modifiedDate(TIME)
                           .severityTypeRefDescription("Low")
                           .lanesAffectedTypeRefDescription("| T")
                           .diversionInForce("N")
                           .phaseTypeRef("current")
                           .eventTypeDescription("")
                           .organiser("An organiser")
                           .venueName("venue")
                           .planned(PLANNED)
                           .build();
    }

    public static TrafficAccident getAccident(String code) {
        return TrafficAccident.builder()
                                 .systemCodeNumber(code)
                                 .type(TrafficDataTypes.ACCIDENT)
                                 .shortDescription("short description")
                                 .longDescription("long description")
                                 .locationDescription("location description")
                                 .point(POINT)
                                 .creationDate(TIME)
                                 .dataSourceTypeRef("type ref")
                                 .confirmedDate(TIME)
                                 .modifiedDate(TIME)
                                 .severityTypeRefDescription("Low")
                                 .lanesAffectedTypeRefDescription("| T")
                                 .diversionInForce("N")
                                 .phaseTypeRef("current")
                                 .accidentTypeDescription("")
                                 .accidentTime(TIME)
                                 .endTime(TIME)
                                 .build();
    }

    public static TrafficRoadwork getRoadwork(String code) {
        return TrafficRoadwork.builder()
                                  .systemCodeNumber(code)
                                  .type(TrafficDataTypes.ROADWORKS)
                                  .shortDescription("short description")
                                  .longDescription("long description")
                                  .locationDescription("location description")
                                  .point(POINT)
                                  .creationDate(TIME)
                                  .dataSourceTypeRef("type ref")
                                  .confirmedDate(TIME)
                                  .modifiedDate(TIME)
                                  .severityTypeRefDescription("Low")
                                  .lanesAffectedTypeRefDescription("| T")
                                  .diversionInForce("N")
                                  .phaseTypeRef("current")
                                  .roadworkTypeDescription("")
                                  .contractor("contractor")
                                  .contraflow("N")
                                  .trafficSignals("N")
                                  .planned(PLANNED)
                                  .actual(PLANNED)
                                  .build();
    }

    public static JourneyTime getJourneyTime(String code) {
        return JourneyTime.builder()
                          .systemCodeNumber(code)
                          .shortDescription("short description")
                          .longDescription("long description")
                          .point(POINT)
                          .endPoint(POINT)
                          .lastUpdated(TIME)
                          .linkTravelTime(100)
                          .platesIn(10)
                          .platesOut(10)
                          .plateMatches(10)
                          .lastUpdatedDynamic(TIME)
                          .build();
    }

    public static JourneyTime getJourneyTimeWithRoute(String code) {
        JourneyTime journeyTime = getJourneyTime(code);
        journeyTime.setRoute(getSimpleRoute());
        return journeyTime;
    }

    public static Camera getCamera(String code) {
        return Camera.builder()
                        .systemCodeNumber(code)
                        .shortDescription(SHORT_DESCRIPTION)
                        .longDescription(LONG_DESCRIPTION)
                        .point(POINT)
                        .lastUpdated(TIME)
                        .lastUpdatedDynamic(TIME)
                        .image(IMAGE_URL)
                        .build();
    }

    public static TypicalJourneyTime getTypicalJourneyTime(String code) {
        return TypicalJourneyTime.builder()
                                 .systemCodeNumber(code)
                                 .id(1L)
                                 .isWeekend(false)
                                 .travelTime(10.0)
                                 .standardDeviation(1.0)
                                 .timeOfDay(LocalTime.now())
                                 .build();
    }

    public static SimpleRoute getSimpleRoute() {
        List<GeoJsonPoint> coordinates = List.of(
                new GeoJsonPoint(59.1, -1.0),
                new GeoJsonPoint(58.9, 1.2));

        return SimpleRoute.builder()
                          .coordinates(coordinates)
                          .distance(627.5)
                          .duration(59.6)
                          .build();
    }

}
