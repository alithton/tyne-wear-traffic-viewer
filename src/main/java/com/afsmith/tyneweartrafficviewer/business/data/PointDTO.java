package com.afsmith.tyneweartrafficviewer.business.data;

/**
 * Represents a geographic location specified by its latitude and longitude.
 * @param latitude The latitude of the location.
 * @param longitude The longitude of the location.
 */
public record PointDTO(
        double latitude,
        double longitude
) { }
