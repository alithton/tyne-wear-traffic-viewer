package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.business.services.CommentService;
import com.afsmith.tyneweartrafficviewer.business.services.DtoService;
import com.afsmith.tyneweartrafficviewer.business.services.TypicalJourneyTimeService;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.exceptions.InvalidTrafficDataException;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for the traffic data API.
 */
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"}, allowCredentials = "true")
@RestController
public class IncidentController {

    private final DtoService dtoService;
    private final TypicalJourneyTimeService typicalJourneyTimeService;
    private final CommentService commentService;

    /**
     * Get a list of all stored traffic data of the requested type. If no data
     * type is specified in the request, incident data will be returned.
     * @param dataTypes The type of data to be returned.
     * @return An HTTP response with the requested data stored in the body in
     * JSON format.
     */
    @GetMapping("/incidents")
    public ResponseEntity<Map<String, List<? extends TrafficDataDTO>>>
        getIncidents(@RequestParam(name="type", required = false) List<TrafficDataTypes> dataTypes,
                     @RequestParam(name="speedType", required = false) SpeedType speedType) {

        Map<String, List<? extends TrafficDataDTO>> response = new HashMap<>();

        // With no data types specified, return a response with an empty body.
        if (dataTypes == null) return ResponseEntity.ok(response);
        if (speedType == null) speedType = SpeedType.CURRENT;

        for (var dataType : dataTypes) {
            List<? extends TrafficDataDTO> incidents;
            if (dataType == TrafficDataTypes.SPEED && speedType != SpeedType.CURRENT) {
                incidents = typicalJourneyTimeService.listAll(speedType);
            } else {
                incidents = dtoService.listAll(dataType);
            }
            response.put(dataType.name(), incidents);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Get the data for the incident specified by the provided code number. If no
     * incident can be found, return a 404 not found exception.
     * @param codeNumber The system code number of the incident.
     * @return An Http response with the requested incident data in the body in
     * JSON format.
     */
    @GetMapping("/incidents/{codeNumber}")
    public @ResponseBody TrafficDataDTO getIncident(@PathVariable String codeNumber) {
        try {
            return dtoService.getIncident(codeNumber);
        } catch (DataNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Serve the image specified by the given code number.
     * @param codeNumber The code number identifying the image.
     * @return An HTTP response with a body containing the image as a binary large object (BLOB).
     */
    @GetMapping(value = "image/{codeNumber}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String codeNumber) {
        return dtoService.getImage(codeNumber);
    }

    /**
     * Add a comment to the traffic incident specified by the provided code number.
     * Note that comments can only be added to data types that subclass
     * {@link com.afsmith.tyneweartrafficviewer.entities.TrafficPointData}. Attempting
     * to add comments to any other data type will result in a 404 NOT FOUND response.
     * In order to add comments, users must be logged in and this
     * method expects a valid authentication cookie.
     * @param codeNumber The system code number of the traffic data to which the comment
     *                   should be added.
     * @param comment The comment being added.
     * @param token An authentication token. This is provided when a user signs up
     *              or provides a valid login.
     */
    @PostMapping(value = "/incidents/{codeNumber}")
    public void addComment(@PathVariable String codeNumber,
                           @RequestBody CommentDTO comment,
                           @CookieValue("token") String token) {
        try {
            commentService.save(codeNumber, token, comment);
        } catch (DataNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (NotAuthenticatedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/incidents")
    @ResponseStatus(HttpStatus.CREATED)
    public void addIncident(@RequestBody NewTrafficDataDTO incident,
                            @CookieValue(name = "token", required = false) String token) {
        try {
            dtoService.save(incident, token);
        } catch (NotAuthenticatedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (InvalidTrafficDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
