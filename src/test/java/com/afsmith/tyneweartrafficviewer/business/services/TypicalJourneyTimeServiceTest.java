package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
        List<JourneyTimeDTO> dtos = service.listAll();
        JourneyTimeDTO refJourneyTime = mapper.entityToDto(MockData.getJourneyTimeWithRoute("ref"));

        assertThat(dtos.size()).isEqualTo(2);
        assertThat(dtos.get(0).getAverageSpeed()).isNotEqualTo(0.0);
        assertThat(dtos.get(0).getAverageSpeed()).isNotEqualTo(refJourneyTime.getAverageSpeed());
    }


}