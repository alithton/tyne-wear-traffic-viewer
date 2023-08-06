package com.afsmith.tyneweartrafficviewer.persistence.routing.client;

import com.afsmith.tyneweartrafficviewer.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.OsrmRoute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Client for the Open Source Routing Machine (OSRM) routing software. Note that
 * as currently implemented, this client assumes that the OSRM software is running
 * on the local machine on port 5000. Instructions for running an OSRM Docker container
 * are available on the project's Github page.
 * <p>
 * Although OSRM can generate routes using a number of different profiles (such driving,
 * cycling etc.), this client only access driving routes.
 */
@RequiredArgsConstructor
@Getter
@Service
public class OsrmClient {
    // URL for the OSRM server.
    private final String BASE_URL = "http://localhost:5000/route/v1/driving/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper;

    /**
     * Get an OSRM route between the provided start and end points.
     * @param start The route start point.
     * @param end The route end point.
     * @return The body of the OSRM server response, in GeoJSON format.
     */
    public String getRouteResponse(GeoJsonPoint start, GeoJsonPoint end) {
        URI uri = constructURI(start, end);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

    /**
     * Get an OSRM route between the provided start and end points.
     * @param start The route start point.
     * @param end The route end point.
     * @return A route object representing the server response.
     */
    public OsrmRoute getRoute(GeoJsonPoint start, GeoJsonPoint end) {
        String responseBody = getRouteResponse(start, end);
        return readRouteFromBody(responseBody);
    }

    // Read the route JSON data into a route object.
    private OsrmRoute readRouteFromBody(String body) {
        try {
            return mapper.readValue(body, OsrmRoute.class);
        } catch (JsonProcessingException e) {
            return new OsrmRoute();
        }
    }

    /*
     * Use the base URL and the provided start and end points to construct a valid
     * request URL for OSRM route data.
     */
    private URI constructURI(GeoJsonPoint start, GeoJsonPoint end) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                                .pathSegment(String.format("%s,%s;%s,%s",
                                                                           start.getLongitudeString(),
                                                                           start.getLatitudeString(),
                                                                           end.getLongitudeString(),
                                                                           end.getLatitudeString()))
                                                .queryParam("geometries", "geojson")
                                                .build();
        return uri.toUri();
    }
}
