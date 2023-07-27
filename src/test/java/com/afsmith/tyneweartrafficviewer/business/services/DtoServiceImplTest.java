package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void listIncidents() {
        when(dataPersistence.listAll(TrafficDataTypes.INCIDENT))
                .thenReturn(List.of(MockData.getIncident("code1"),
                                    MockData.getIncident("code2")));

        List<TrafficIncidentDTO> incidentDTOS = dtoService.listAll(TrafficDataTypes.INCIDENT);

        assertThat(incidentDTOS.size()).isEqualTo(2);
    }

}