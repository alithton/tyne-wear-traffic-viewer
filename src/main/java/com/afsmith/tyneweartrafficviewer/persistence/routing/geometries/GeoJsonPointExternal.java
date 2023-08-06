package com.afsmith.tyneweartrafficviewer.persistence.routing.geometries;

import com.afsmith.tyneweartrafficviewer.persistence.routing.services.deserialisation.GeoJsonPointDeserialiser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

/**
 * Represents a point (a pair of coordinates) returned by the OSRM server. These
 * are provided by the server in GeoJson format.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = GeoJsonPointDeserialiser.class)
public class GeoJsonPointExternal {
    private double latitude;
    private double longitude;
}
