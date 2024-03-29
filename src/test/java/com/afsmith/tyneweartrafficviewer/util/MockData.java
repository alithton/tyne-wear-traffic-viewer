package com.afsmith.tyneweartrafficviewer.util;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public class MockData {
    public static final ZonedDateTime TIME = ZonedDateTime.now();
    public static final ZonedDateTime END_TIME = TIME.plusHours(1L);
    public static final double LATITUDE = 96.0;
    public static final double LONGITUDE = -1.0;
    private static final PointDTO POINT_DTO = new PointDTO(LATITUDE, LONGITUDE);
    private static final Point POINT = new Point(LATITUDE, LONGITUDE);
    private static final PlannedTimes PLANNED = new PlannedTimes(TIME, END_TIME);
    private static final PlannedDTO PLANNED_DTO = new PlannedDTO(TIME, END_TIME);
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
                              .type(TrafficDataTypes.EVENT)
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
                             .build();
    }

    public static ComparisonDTO getComparisonDTO(String code, double typicalSpeed, double currentSpeed) {
        return ComparisonDTO.comparisonBuilder()
                            .shortDescription(SHORT_DESCRIPTION)
                            .longDescription(LONG_DESCRIPTION)
                            .systemCodeNumber(code)
                            .speed(currentSpeed)
                            .typicalSpeed(typicalSpeed)
                            .build();
    }

    public static ComparisonDTO getComparisonDTO(String code) {
        return getComparisonDTO(code, 30.0, 30.0);
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

    public static NewTrafficDataDTO getNewTrafficDataDto(TrafficDataTypes dataType) {
        return NewTrafficDataDTO.builder()
                                .type(dataType)
                                .shortDescription(SHORT_DESCRIPTION)
                                .longDescription(LONG_DESCRIPTION)
                                .locationDescription(LOCATION_DESCRIPTION)
                                .latitude(LATITUDE)
                                .longitude(LONGITUDE)
                                .start(TIME)
                                .end(END_TIME)
                                .diversionInForce(DIVERSION)
                                .severityTypeRefDescription(SEVERITY)
                                .lanesAffectedTypeRefDescription(LANES_AFFECTED)
                                .phaseTypeRef(PHASE_TYPE)
                                .build();
    }

    public static CommentDTO getCommentDto(String username) {
        return CommentDTO.builder()
                .userName(username)
                .content("content")
                .created(TIME)
                         .build();
    }

    // Get Mock entities

    public static TrafficIncident getIncident(String code) {
        return TrafficIncident.builder()
                              .systemCodeNumber(code)
                              .type(TrafficDataTypes.INCIDENT)
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
                              .incidentTypeDescription(TYPE_DESCRIPTION)
                              .incidentTime(TIME)
                              .endTime(END_TIME)
                              .build();
    }

    public static TrafficIncident getIncident(String code, String severity) {
        TrafficIncident incident = getIncident(code);
        incident.setSeverityTypeRefDescription(severity);
        return incident;
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
                           .eventTypeDescription(TYPE_DESCRIPTION)
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
                                 .accidentTypeDescription(TYPE_DESCRIPTION)
                                 .accidentTime(TIME)
                                 .endTime(END_TIME)
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
                                  .roadworkTypeDescription(TYPE_DESCRIPTION)
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
                                 .isWeekend(false)
                                 .travelTime(10.0)
                                 .standardDeviation(1.0)
                                 .timeOfDay(LocalTime.NOON)
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

    public static User getUser(String username, String password) {
        Credentials credentials = new Credentials(username, password);
        return new User(credentials);
    }

    public static Comment getComment(String content, User user, TrafficPointData pointData) {
        return Comment.builder()
                      .content(content)
                      .created(TIME)
                      .user(user)
                      .trafficData(pointData)
                      .build();
    }

}
