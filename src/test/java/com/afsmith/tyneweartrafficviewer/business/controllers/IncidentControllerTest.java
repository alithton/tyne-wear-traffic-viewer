package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.business.services.DtoService;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(IncidentController.class)
@Import(DtoService.class)
class IncidentControllerTest {

    String URL_PATH = "/incidents";
    String DATA_TYPE_PARAM = "type";

    @MockBean
    DtoService dtoService;

    @Autowired
    MockMvc mockMvc;

    List<MappableDTO> incidentList;
    List<MappableDTO> eventList;

    @BeforeEach
    void setUp() {
        incidentList = List.of(MockData.getIncidentDto("code1"),
                               MockData.getIncidentDto("code2"));

        eventList = List.of(MockData.getEventDto("code1"));
    }

    @Test
    void getIncidentsNoRequestParam() throws Exception {

        when(dtoService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);

        mockMvc.perform(get(URL_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getIncidentsTypeIncident() throws Exception {
        when(dtoService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.INCIDENT.length()", is(2)));
    }

    @Test
    void getIncidentsTypeEvent() throws Exception {
        when(dtoService.listAll(TrafficDataTypes.EVENT)).thenReturn(eventList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "EVENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.EVENT.length()", is(1)));
    }

    @Test
    void getIncidentsTypeAccident() throws Exception {
        List<MappableDTO> accidentList = List.of(
                MockData.getAccidentDto("code 1"),
                MockData.getAccidentDto("code 2"));
        when(dtoService.listAll(TrafficDataTypes.ACCIDENT)).thenReturn(accidentList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "ACCIDENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.ACCIDENT.length()", is(2)));
    }

    @Test
    void getIncidentsTypeRoadwork() throws Exception {
        List<MappableDTO> roadworkList = List.of(
                MockData.getRoadworkDto("code 1"),
                MockData.getRoadworkDto("code 2"));
        when(dtoService.listAll(TrafficDataTypes.ROADWORKS)).thenReturn(roadworkList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "ROADWORKS"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.ROADWORKS.length()", is(2)));
    }

    @Test
    void getIncidentsMultipleDataTypes() throws Exception {
        when(dtoService.listAll(TrafficDataTypes.INCIDENT)).thenReturn(incidentList);
        when(dtoService.listAll(TrafficDataTypes.EVENT)).thenReturn(eventList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT", "EVENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }
}