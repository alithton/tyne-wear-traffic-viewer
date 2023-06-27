package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import org.junit.jupiter.api.BeforeEach;
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
    TrafficDataPersistence persistenceService;
    List<TrafficDataDTO> incidentList;
    List<TrafficDataDTO> eventList;

    @BeforeEach
    void setUp() {
        var point = new PointDTO(100L, 100L, 0.0, 0.0);
        ZonedDateTime currentTime = ZonedDateTime.now();
        var plannedTime = new PlannedDTO(currentTime, currentTime);
        incidentList = List.of(
                new TrafficIncidentDTO(
                        "code1",
                        TrafficDataTypes.INCIDENT,
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
                        "incident_type",
                        currentTime,
                        currentTime
                ),
                new TrafficIncidentDTO(
                        "code2",
                        TrafficDataTypes.INCIDENT,
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
                        "incident_type",
                        currentTime,
                        currentTime
                )
        );

        eventList = List.of(
                new TrafficEventDTO(
                        "event1",
                        TrafficDataTypes.EVENT,
                        "short description",
                        "long description",
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
                        "SHOW",
                        plannedTime,
                        "organiser",
                        "venue"

                )
        );
    }

    @Test
    void getIncidentsNoRequestParam() throws Exception {

        when(persistenceService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);

        mockMvc.perform(get("/incidents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void getIncidentsTypeIncident() throws Exception {
        when(persistenceService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);

        mockMvc.perform(get("/incidents").queryParam("type", "INCIDENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void getIncidentsTypeEvent() throws Exception {
        when(persistenceService.listAll(TrafficDataTypes.EVENT)).thenReturn(eventList);

        mockMvc.perform(get("/incidents").queryParam("type", "EVENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(1)));
    }
}