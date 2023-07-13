package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * A simplified representation of a route that contains only the data necessary
 * for the Traffic Viewer app. This representation is not tied to any particular
 * API route response format.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class SimpleRoute {

    // A list of point coordinates that defines the route.
    @ElementCollection
    @OrderColumn(name = "coord_index")
    private List<GeoJsonPoint> coordinates;

    // The distance of the route, in metres.
    private double distance;

    // The estimated time taken to complete the route, in seconds.
    private double duration;
}
