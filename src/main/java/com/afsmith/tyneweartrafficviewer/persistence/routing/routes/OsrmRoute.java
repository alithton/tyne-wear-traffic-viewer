package com.afsmith.tyneweartrafficviewer.persistence.routing.routes;

import com.afsmith.tyneweartrafficviewer.persistence.routing.geometries.GeoJsonPointExternal;
import lombok.*;

import java.util.List;

/**
 * A representation of the route JSON response data received from the OSRM server.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OsrmRoute {
    private String code;
    private List<Route> routes;
    private List<Waypoint> waypoints;

    @Data
    public static class Route {
        private Geometry geometry;
        private List<Leg> legs;
        private String weight_name;
        private double weight;
        private double duration;
        private double distance;

    }

    @Data
    public static class Geometry {
        private List<GeoJsonPointExternal> coordinates;
        private String type;
    }

    @Data
    public static class Leg {
        private List<String> steps;
        private String summary;
        private double weight;
        private double duration;
        private double distance;
    }

    @Data
    public static class Waypoint {
        private String hint;
        private double distance;
        private String name;
        private List<Double> location;
    }
}
