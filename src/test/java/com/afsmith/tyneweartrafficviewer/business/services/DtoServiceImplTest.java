package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.NewTrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.InvalidTrafficDataException;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {
        DtoServiceImpl.class,
        TrafficIncidentMapperImpl.class,
        TrafficEventMapperImpl.class,
        TrafficAccidentMapperImpl.class,
        TrafficRoadworkMapperImpl.class,
        JourneyTimeMapperImpl.class,
        CameraMapperImpl.class,
        CommentMapperImpl.class
})
class DtoServiceImplTest {

    @Autowired
    DtoService dtoService;

    @MockBean
    TrafficDataPersistence dataPersistence;

    @MockBean
    UserService userService;

    @Test
    void listIncidents() {
        when(dataPersistence.listAll(TrafficDataTypes.INCIDENT))
                .thenReturn(List.of(MockData.getIncident("code1"),
                                    MockData.getIncident("code2")));

        List<TrafficIncidentDTO> incidentDTOS = dtoService.listAll(TrafficDataTypes.INCIDENT);

        assertThat(incidentDTOS.size()).isEqualTo(2);
    }

    @Test
    void save() throws Exception {
        User USER = MockData.getUser("user1", "secret_pass");
        NewTrafficDataDTO newData = MockData.getNewTrafficDataDto(TrafficDataTypes.INCIDENT);
        when(userService.findByToken(anyString()))
                .thenReturn(USER);

        dtoService.save(newData, "token");

        ArgumentCaptor<TrafficPointData> pointDataCaptor = ArgumentCaptor.forClass(TrafficPointData.class);

        verify(userService, times(1)).findByToken(anyString());
        verify(dataPersistence, times(1)).persist(pointDataCaptor.capture(), any(TrafficDataTypes.class));

        TrafficPointData captured = pointDataCaptor.getValue();

        assertThat(captured).isNotNull();
        assertThat(captured).isInstanceOf(TrafficIncident.class);
        assertThat(captured.getCreatedBy()).isEqualTo(USER);
    }

    @Test
    void saveThrowsInvalidDataException() {
        NewTrafficDataDTO emptyData = new NewTrafficDataDTO();
        assertThatExceptionOfType(InvalidTrafficDataException.class)
                .isThrownBy(() -> dtoService.save(emptyData, "token"));
    }

}