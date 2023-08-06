package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * A data transfer object for transferring traffic camera data to the frontend.
 */
@Getter
public class CameraDTO extends TrafficDataDTO {
    String shortDescription;
    String longDescription;
    PointDTO point;
    ZonedDateTime lastUpdated;
    URL image;
    ZonedDateTime lastUpdatedDynamic;

    /**
     * An all-arguments constructor for traffic camera data transfer objects.
     */
    @Builder
    public CameraDTO(String systemCodeNumber,
                     String shortDescription,
                     String longDescription,
                     PointDTO point,
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
