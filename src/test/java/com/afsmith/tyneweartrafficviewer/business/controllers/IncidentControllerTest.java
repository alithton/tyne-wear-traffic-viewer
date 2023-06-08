package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.PointDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataServiceIncidents;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(IncidentController.class)
class IncidentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrafficDataServiceIncidents incidentsService;

    @Test
    void getIncidents() throws Exception {
        var point = new PointDTO(100L, 100L, 0.0, 0.0);
        ZonedDateTime currentTime = ZonedDateTime.now();
        TrafficIncidentDTO incident1 = new TrafficIncidentDTO(
                "code1",
                "type",
                "incident_type",
                "short_description",
                "long_description",
                "location",
                point,
                currentTime,
                "typeRef",
                currentTime,
                currentTime,
                "Low",
                "T T",
                "N",
                "Current",
                currentTime,
                currentTime
                );
        TrafficIncidentDTO incident2 = new TrafficIncidentDTO(
                "code2",
                "type",
                "incident_type",
                "short_description",
                "long_description",
                "location",
                point,
                currentTime,
                "typeRef",
                currentTime,
                currentTime,
                "Low",
                "T T",
                "N",
                "Current",
                currentTime,
                currentTime
        );

        when(incidentsService.listAll()).thenReturn(List.of(incident1, incident2));

        mockMvc.perform(get("/incidents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }
}