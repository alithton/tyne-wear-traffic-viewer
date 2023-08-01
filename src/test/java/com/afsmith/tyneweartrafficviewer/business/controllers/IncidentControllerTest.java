package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.business.services.CommentService;
import com.afsmith.tyneweartrafficviewer.business.services.DtoService;
import com.afsmith.tyneweartrafficviewer.business.services.TypicalJourneyTimeService;
import com.afsmith.tyneweartrafficviewer.business.services.filter.SpeedType;
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

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(IncidentController.class)
@Import(DtoService.class)
class IncidentControllerTest {

    String URL_PATH = "/incidents";
    String DATA_TYPE_PARAM = "type";
    String SPEED_TYPE_PARAM = "speedType";

    @MockBean
    DtoService dtoService;

    @MockBean
    TypicalJourneyTimeService typicalJourneyTimeService;

    @MockBean
    CommentService commentService;

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
        when(dtoService.listAll(eq(TrafficDataTypes.INCIDENT), any())).thenReturn(incidentList);

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.INCIDENT.length()", is(2)));
    }

    @Test
    void getIncidentsTypeEvent() throws Exception {
        when(dtoService.listAll(eq(TrafficDataTypes.EVENT), any())).thenReturn(eventList);

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
        when(dtoService.listAll(eq(TrafficDataTypes.ACCIDENT), any())).thenReturn(accidentList);

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
        when(dtoService.listAll(eq(TrafficDataTypes.ROADWORKS), any())).thenReturn(roadworkList);

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

    @Test
    void getTypicalSpeed() throws Exception {
        when(typicalJourneyTimeService.listAll(SpeedType.TYPICAL))
                .thenReturn(List.of(MockData.getJourneyTimeDto("code1"),
                            MockData.getJourneyTimeDto("code2")));

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "SPEED")
                                .queryParam(SPEED_TYPE_PARAM, "TYPICAL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.SPEED.length()", is(2)));
    }

    @Test
    void getSpeedComparison() throws Exception {
        when(typicalJourneyTimeService.listAll(SpeedType.COMPARISON))
                .thenReturn(List.of(MockData.getComparisonDTO("code1"),
                                    MockData.getComparisonDTO("code2")));

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "SPEED")
                                .queryParam(SPEED_TYPE_PARAM, "COMPARISON"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(1)))
               .andExpect(jsonPath("$.SPEED.length()", is(2)));
    }

    @Test
    void getIncident() throws Exception {
        String codeNumber = "code1";
        when(dtoService.getIncident(codeNumber))
                .thenReturn(MockData.getIncidentDto(codeNumber));

        mockMvc.perform(get(URL_PATH + "/" + codeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.systemCodeNumber", is(codeNumber)));
    }

    @Test
    void getImage() throws Exception {
        String codeNumber = "code1";
        byte[] returnByte = new byte[0];
        when(dtoService.getImage(codeNumber))
                .thenReturn(returnByte);

        mockMvc.perform(get("/image/" + codeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(returnByte));
    }
}