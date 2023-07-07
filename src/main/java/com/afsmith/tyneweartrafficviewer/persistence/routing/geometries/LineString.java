package com.afsmith.tyneweartrafficviewer.persistence.routing.geometries;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record LineString(
        @JsonAlias({"coordinates", "route"})
        List<GeoJsonPoint> route
) {
}
