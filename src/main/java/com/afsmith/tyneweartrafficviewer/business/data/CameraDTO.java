package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.time.ZonedDateTime;

@Getter
public class CameraDTO extends TrafficDataDTO {
    String shortDescription;
    String longDescription;
    PointDTO point;
    ZonedDateTime lastUpdated;
    URL image;
    ZonedDateTime lastUpdatedDynamic;

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
