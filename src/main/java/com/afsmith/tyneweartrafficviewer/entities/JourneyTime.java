package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class JourneyTime extends TrafficData {
    String shortDescription;
    String longDescription;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "easting", column = @Column(name = "start_easting")),
            @AttributeOverride(name = "northing", column = @Column(name = "start_northing")),
            @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude")),
    })
    Point point;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "easting", column = @Column(name = "end_easting")),
            @AttributeOverride(name = "northing", column = @Column(name = "end_northing")),
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