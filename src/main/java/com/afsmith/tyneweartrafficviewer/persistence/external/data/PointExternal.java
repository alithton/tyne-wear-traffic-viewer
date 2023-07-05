package com.afsmith.tyneweartrafficviewer.persistence.external.data;

public record PointExternal(
        Long easting,
        Long northing,
        double latitude,
        double longitude
) { }
