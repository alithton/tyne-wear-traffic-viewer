package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.business.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TypicalJourneyTimeServiceTest {

    TypicalJourneyTimeService service;
    JourneyTimeMapper mapper = Mappers.getMapper(JourneyTimeMapper.class);
    @Mock(strictness = Mock.Strictness.LENIENT)
    TrafficDataPersistence dataPersistence;

    @BeforeEach
    void setUp() {
        service = new TypicalJourneyTimeService(dataPersistence, mapper);

        when(dataPersistence.listAll(TrafficDataTypes.SPEED))
                .thenReturn(List.of(MockData.getJourneyTimeWithRoute("code1"),
                                    MockData.getJourneyTimeWithRoute("code2")));

        when(dataPersistence.findTypicalJourneyTimesByTime(any(LocalTime.class), anyBoolean()))
                .thenReturn(List.of(MockData.getTypicalJourneyTime("code1"),
                                    MockData.getTypicalJourneyTime("code2")));
    }

    @Test
    void listAllDTOsGetAverageSpeedFromTypicalJourneyTime() {
        List<JourneyTimeDTO> dtos = service.listAll(SpeedType.TYPICAL);
        JourneyTimeDTO refJourneyTime = mapper.entityToDto(MockData.getJourneyTimeWithRoute("ref"));

        assertThat(dtos.size()).isEqualTo(2);
        assertThat(dtos.get(0).getSpeed()).isNotEqualTo(0.0);
        assertThat(dtos.get(0).getSpeed()).isNotEqualTo(refJourneyTime.getSpeed());
    }

    @Test
    void listAllSpeedComparisons() {
        List<ComparisonDTO> dtos = service.listAll(SpeedType.COMPARISON);

        assertThat(dtos.size()).isEqualTo(2);
        assertThat(dtos.get(0).getComparison()).isEqualTo(ComparisonResult.MUCH_SLOWER);
    }


}