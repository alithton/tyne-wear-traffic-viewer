package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
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

    String URL_PATH = "/incidents";
    String DATA_TYPE_PARAM = "type";

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

        mockMvc.perform(get(URL_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getIncidentsTypeIncident() throws Exception {
        when(persistenceService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.INCIDENT.length()", is(2)));
    }

    @Test
    void getIncidentsTypeEvent() throws Exception {
        when(persistenceService.listAll(TrafficDataTypes.EVENT)).thenReturn(eventList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "EVENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.EVENT.length()", is(1)));
    }

    @Test
    void getIncidentsTypeAccident() throws Exception {
        List<TrafficDataDTO> accidentList = List.of(
                MockData.getAccidentDto("code 1"),
                MockData.getAccidentDto("code 2"));
        when(persistenceService.listAll(TrafficDataTypes.ACCIDENT)).thenReturn(accidentList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "ACCIDENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.ACCIDENT.length()", is(2)));
    }

    @Test
    void getIncidentsTypeRoadwork() throws Exception {
        List<TrafficDataDTO> roadworkList = List.of(
                MockData.getRoadworkDto("code 1"),
                MockData.getRoadworkDto("code 2"));
        when(persistenceService.listAll(TrafficDataTypes.ROADWORKS)).thenReturn(roadworkList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "ROADWORKS"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.ROADWORKS.length()", is(2)));
    }

    @Test
    void getIncidentsMultipleDataTypes() throws Exception {
        when(persistenceService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);
        when(persistenceService.listAll(TrafficDataTypes.EVENT)).thenReturn(eventList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT", "EVENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }
}