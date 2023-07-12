package com.afsmith.tyneweartrafficviewer.persistence.routing.geometries;

import com.afsmith.tyneweartrafficviewer.persistence.routing.services.deserialisation.GeoJsonPointDeserialiser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = GeoJsonPointDeserialiser.class)
public class GeoJsonPointExternal {
    private double latitude;
    private double longitude;
}
