package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.data.CommentDTO;
import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDTO;
import com.afsmith.tyneweartrafficviewer.business.services.CommentService;
import com.afsmith.tyneweartrafficviewer.business.services.DtoService;
import com.afsmith.tyneweartrafficviewer.business.services.TypicalJourneyTimeService;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import com.afsmith.tyneweartrafficviewer.business.services.filter.SpeedType;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.cookies.CookieDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@WebMvcTest(IncidentController.class)
@Import(DtoService.class)
class IncidentControllerTest {

    String URL_PATH = "/incidents";
    String DATA_TYPE_PARAM = "type";
    String SPEED_TYPE_PARAM = "speedType";
    String SEVERITY_PARAM = "severity";
    String INCLUDE_CUSTOM_PARAM = "includeCustomIncidents";
    String CURRENT_ONLY_PARAM = "currentOnly";
    String START_DATE_PARAM = "startDate";
    String END_DATE_PARAM = "endDate";

    @MockBean
    DtoService dtoService;

    @MockBean
    TypicalJourneyTimeService typicalJourneyTimeService;

    @MockBean
    CommentService commentService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    List<TrafficDTO> incidentList;
    List<TrafficDTO> eventList;

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
        List<TrafficDTO> accidentList = List.of(
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
        List<TrafficDTO> roadworkList = List.of(
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
    void getAllDataTypes() throws Exception {
        when(dtoService.listAll(eq(TrafficDataTypes.INCIDENT), any(FilterService.class)))
                .thenReturn(List.of(MockData.getIncidentDto("code")));
        when(dtoService.listAll(eq(TrafficDataTypes.EVENT), any(FilterService.class)))
                .thenReturn(List.of(MockData.getEventDto("code")));

        mockMvc.perform(get(URL_PATH).queryParam(DATA_TYPE_PARAM, "INCIDENT", "EVENT"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$.INCIDENT.length()", is(1)))
               .andDo(document("incidents",
                               preprocessResponse(prettyPrint(),
                                                  modifyHeaders().remove("Content-Length")
                                                                 .remove("Vary")),
                               queryParameters(parameterWithName(DATA_TYPE_PARAM).description("The type of traffic data.").optional()
                                                      .attributes(),
                                              parameterWithName(SPEED_TYPE_PARAM).description("The type of speed data to fetch.").optional(),
                                              parameterWithName(SEVERITY_PARAM).description("Fetch incidents of the specified severity.").optional(),
                                              parameterWithName(INCLUDE_CUSTOM_PARAM).description("Should user-created incident data be included?").optional(),
                                              parameterWithName(CURRENT_ONLY_PARAM).description("Should only currently active incidents be fetched?").optional(),
                                              parameterWithName(START_DATE_PARAM).description("The start of the date range for which to fetch data.").optional(),
                                              parameterWithName(END_DATE_PARAM).description("The end of the date range for which to fetch data.").optional()
                               )
               ));;

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

        mockMvc.perform(get(URL_PATH + "/{codeNumber}", codeNumber))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.systemCodeNumber", is(codeNumber)))
               .andDo(document("getIncident",
                               preprocessResponse(prettyPrint(),
                                                  modifyHeaders().remove("Content-Length")
                                                                 .remove("Vary")),
                               pathParameters(parameterWithName("codeNumber").description(
                                       "System code number of the incident data to be retrieved"))
               ));
    }

    @Test
    void getImage() throws Exception {
        String codeNumber = "code1";
        byte[] returnByte = new byte[0];
        when(dtoService.getImage(codeNumber))
                .thenReturn(returnByte);

        mockMvc.perform(get("/image/{codeNumber}", codeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(returnByte))
                .andDo(document("getImage",
                                preprocessResponse(modifyHeaders().set("Content-Type", "image/jpeg")
                                                                  .remove("Vary")),
                                pathParameters(parameterWithName("codeNumber").description(
                                        "System code number of the camera from which to get the image."))
                                ));
    }

    @Test
    void addComment() throws Exception {
        CommentDTO comment = MockData.getCommentDto("username");
        Cookie token = new Cookie("token", "value");
        String codeNumber = "code";

        mockMvc.perform(post(URL_PATH + "/{codeNumber}", codeNumber)
                                .content(mapper.writeValueAsString(comment))
                                .contentType(MediaType.APPLICATION_JSON)
                                .cookie(token))
                .andExpect(status().isCreated())
                .andDo(document("addComment",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(modifyHeaders().remove("Vary")),
                                requestCookies(cookieWithName("token").description("Authentication token.")),
                                pathParameters(parameterWithName("codeNumber").description(
                                        "System code number of the traffic incident to which to add the comment."))
                ));
    }

    @Test
    void addIncident() throws Exception {
        NewTrafficDataDTO newIncident = MockData.getNewTrafficDataDto(TrafficDataTypes.INCIDENT);
        Cookie token = new Cookie("token", "value");

        mockMvc.perform(post(URL_PATH)
                                .content(mapper.writeValueAsString(newIncident))
                                .contentType(MediaType.APPLICATION_JSON)
                                .cookie(token))
                .andExpect(status().isCreated())
                .andDo(document("createIncident",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(modifyHeaders().remove("Vary")),
                                requestCookies(cookieWithName("token").description("Authentication token."))
                                ));
    }
}