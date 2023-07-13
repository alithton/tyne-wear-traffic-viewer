package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.*;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DtoServiceImplTest {

    DtoService dtoService;
    TrafficIncidentMapper incidentMapper = Mappers.getMapper(TrafficIncidentMapper.class);
    TrafficEventMapper eventMapper = Mappers.getMapper(TrafficEventMapper.class);
    TrafficAccidentMapper accidentMapper = Mappers.getMapper(TrafficAccidentMapper.class);
    TrafficRoadworkMapper roadworkMapper = Mappers.getMapper(TrafficRoadworkMapper.class);
    JourneyTimeMapper journeyTimeMapper = Mappers.getMapper(JourneyTimeMapper.class);
    CameraMapper cameraMapper = Mappers.getMapper(CameraMapper.class);

    @Mock
    TrafficDataPersistence dataPersistence;

    @BeforeEach
    void setUp() {
        dtoService = new DtoServiceImpl(incidentMapper, eventMapper, accidentMapper,
                                        roadworkMapper, journeyTimeMapper, cameraMapper, dataPersistence);
    }

    @Test
    void listIncidents() {
        when(dataPersistence.listAll(TrafficDataTypes.INCIDENT))
                .thenReturn(List.of(MockData.getIncident("code1"),
                                    MockData.getIncident("code2")));

        List<TrafficIncidentDTO> incidentDTOS = dtoService.listAll(TrafficDataTypes.INCIDENT);

        assertThat(incidentDTOS.size()).isEqualTo(2);
    }

}