package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * Represents journey time data.
 */
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue("journey_time")
@Entity
public class JourneyTime extends TrafficData {
    String shortDescription;
    String longDescription;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude")),
    })
    Point point;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "end_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "end_longitude")),
    })
    Point endPoint;

    @Setter
    SimpleRoute route;

    ZonedDateTime lastUpdated;
    int linkTravelTime;
    int platesIn;
    int platesOut;
    int plateMatches;
    ZonedDateTime lastUpdatedDynamic;

    /**
     * An all-arguments constructor for journey time data.
     */
    @Builder
    public JourneyTime(String systemCodeNumber,
                       String shortDescription,
                       String longDescription,
                       Point point,
                       Point endPoint,
                       SimpleRoute route,
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
        this.lastUpdated = lastUpdated;
        this.linkTravelTime = linkTravelTime;
        this.platesIn = platesIn;
        this.platesOut = platesOut;
        this.plateMatches = plateMatches;
        this.lastUpdatedDynamic = lastUpdatedDynamic;
    }
}
