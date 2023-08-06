package com.afsmith.tyneweartrafficviewer.persistence.external.data;

/**
 * Represents a geographic location in two coordinate systems. There is the easting and northing of
 * the British National Grid (BNG) system and latitude and longitude of the WGS84 system.
 * @param easting BNG easting.
 * @param northing BNG northing.
 * @param latitude WGS84 latitude.
 * @param longitude WGS84 longitude.
 */
public record PointExternal(
        Long easting,
        Long northing,
        double latitude,
        double longitude
) { }
