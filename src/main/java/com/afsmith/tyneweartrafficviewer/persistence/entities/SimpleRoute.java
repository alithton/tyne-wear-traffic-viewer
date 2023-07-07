package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPoint;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OrderColumn;
import lombok.*;

import java.util.List;

/**
 * A simplified representation of a route that contains only the data necessary
 * for the Traffic Viewer app. This representation is not tied to any particular
 * API route response format.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class SimpleRoute {
    // A list of point coordinates that defines the route.
    @ElementCollection
    @OrderColumn(name = "coord_index")
    List<GeoJsonPoint> coordinates;

    // The distance of the route, in metres.
    double distance;

    // The estimated time taken to complete the route, in seconds.
    double duration;
}
