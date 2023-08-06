package com.afsmith.tyneweartrafficviewer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a geographic location specified by its latitude and longitude. It is
 * formatted as an array when serialised to JSON format.
 * <p>
 *     There is a lot of overlap between this class and the Point data class, to the
 *     extend that they should probably be merged into one. The GeoJsonPoint class
 *     is used when interfacing with the routing service and in the representation
 *     of points along routes.
 * </p>
 */
@Embeddable
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public final class GeoJsonPoint {
    private double latitude;
    private double longitude;

    /**
     * Constructor for the point.
     * @param latitude The point's latitude.
     * @param longitude The point's longitude.
     */
    public GeoJsonPoint(
            double latitude,
            double longitude
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get the latitude of the point as a text string.
     * @return The latitude as a string.
     */
    @JsonIgnore
    public String getLatitudeString() {
        return Double.toString(latitude);
    }

    /**
     * Get the longitude of the point as a text string.
     * @return The longitude as a string.
     */
    @JsonIgnore
    public String getLongitudeString() {
        return Double.toString(longitude);
    }

}
