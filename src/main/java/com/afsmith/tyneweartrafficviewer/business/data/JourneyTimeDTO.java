package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import lombok.*;

import java.time.ZonedDateTime;

@ToString
@Getter
@NoArgsConstructor
public class JourneyTimeDTO extends TrafficDataDTO implements MappableDTO {
    String shortDescription;
    String longDescription;
    PointDTO point;
    PointDTO endPoint;
    SimpleRouteDTO route;
    double averageSpeed;
    ZonedDateTime lastUpdated;
    int linkTravelTime;
    int platesIn;
    int platesOut;
    int plateMatches;
    ZonedDateTime lastUpdatedDynamic;

    @Builder
    public JourneyTimeDTO(String systemCodeNumber,
                          String shortDescription,
                          String longDescription,
                          PointDTO point,
                          PointDTO endPoint,
                          SimpleRouteDTO route,
                          double averageSpeed,
                          ZonedDateTime lastUpdated,
                          int linkTravelTime,
                          int platesIn,
                          int platesOut,
                          int plateMatches,
                          ZonedDateTime lastUpdatedDynamic) {
        super(systemCodeNumber);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.point = point;
        this.endPoint = endPoint;
        this.route = route;
        this.averageSpeed = averageSpeed;
        this.lastUpdated = lastUpdated;
        this.linkTravelTime = linkTravelTime;
        this.platesIn = platesIn;
        this.platesOut = platesOut;
        this.plateMatches = plateMatches;
        this.lastUpdatedDynamic = lastUpdatedDynamic;
    }
}
