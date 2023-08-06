package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A simplified representation of a route that contains only the data necessary
 * for the Traffic Viewer app. This representation is not tied to any particular
 * API route response format.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRouteDTO {
    private List<GeoJsonPointDTO> coordinates;
    private double distance;
    private double duration;
}
