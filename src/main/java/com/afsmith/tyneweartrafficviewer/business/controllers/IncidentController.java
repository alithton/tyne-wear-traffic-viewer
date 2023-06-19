package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataServiceIncidents;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"})
@RestController
public class IncidentController {

    TrafficDataServiceIncidents incidentService;
    @GetMapping("/incidents")
    public ResponseEntity<List<TrafficIncidentDTO>> getIncidents() {
        List<TrafficIncidentDTO> incidents = incidentService.listAll();
        return ResponseEntity.ok(incidents);
    }
}
