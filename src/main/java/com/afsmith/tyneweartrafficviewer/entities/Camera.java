package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * Represents traffic camera data.
 */
@Getter
@NoArgsConstructor
@Entity
public class Camera extends TrafficData {
    String shortDescription;
    String longDescription;
    Point point;
    ZonedDateTime lastUpdated;
    URL image;
    ZonedDateTime lastUpdatedDynamic;

    /**
     * Constructor for traffic camera data instance.
     */
    @Builder
    public Camera(String systemCodeNumber,
                  String shortDescription,
                  String longDescription,
                  Point point,
                  ZonedDateTime lastUpdated,
                  URL image,
                  ZonedDateTime lastUpdatedDynamic) {
        super(systemCodeNumber);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.point = point;
        this.lastUpdated = lastUpdated;
        this.image = image;
        this.lastUpdatedDynamic = lastUpdatedDynamic;
    }
}
