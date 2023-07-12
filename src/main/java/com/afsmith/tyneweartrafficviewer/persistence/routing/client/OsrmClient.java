package com.afsmith.tyneweartrafficviewer.persistence.routing.client;

import com.afsmith.tyneweartrafficviewer.persistence.entities.GeoJsonPoint;
import com.afsmith.tyneweartrafficviewer.persistence.routing.routes.GeoJsonRouteExternal;
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

@RequiredArgsConstructor
@Getter
@Service
public class OsrmClient {
    private final String BASE_URL = "http://localhost:5000/route/v1/driving/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper;

    public String getRouteResponse(GeoJsonPoint start, GeoJsonPoint end) {
        URI uri = constructURI(start, end);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

    public GeoJsonRouteExternal getRoute(GeoJsonPoint start, GeoJsonPoint end) {
        String responseBody = getRouteResponse(start, end);
        return readRouteFromBody(responseBody);
    }

    public GeoJsonRouteExternal readRouteFromBody(String body) {
        try {
            return mapper.readValue(body, GeoJsonRouteExternal.class);
        } catch (JsonProcessingException e) {
            return new GeoJsonRouteExternal();
        }
    }

    private URI constructURI(GeoJsonPoint start, GeoJsonPoint end) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                                .pathSegment(String.format("%s,%s;%s,%s", start.getLongitudeString(),
                                                                           start.getLatitudeString(),
                                                                           end.getLongitudeString(),
                                                                           end.getLatitudeString()))
                                                .queryParam("geometries", "geojson")
                                                .build();
        return uri.toUri();
    }
}
