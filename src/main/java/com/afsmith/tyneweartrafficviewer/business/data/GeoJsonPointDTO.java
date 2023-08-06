package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a geographic location specified by its latitude and longitude.
 */
@ToString
@EqualsAndHashCode
@Getter
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public final class GeoJsonPointDTO {
    private final double latitude;
    private final double longitude;

    /**
     * Constructor for the point.
     * @param latitude The point's latitude.
     * @param longitude The point's longitude.
     */
    public GeoJsonPointDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
