package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TrafficDataRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TrafficDataAccessServiceImplTest {

    @Mock
    TrafficDataRepository repo;

    @InjectMocks
    TrafficDataAccessServiceImpl dataAccessService;

    String code = "1";

    @Test
    void listAll_ListsDataOfDataType_WhenDataTypeProvided() {

        JourneyTime mockJourneyTime = MockData.getJourneyTime(code);
        when(repo.findByDataType(JourneyTime.class)).thenReturn(List.of(mockJourneyTime));

        List<JourneyTime> journeyTimes = dataAccessService.listAll(JourneyTime.class);
        assertThat(journeyTimes.size()).isEqualTo(1);
    }

    @Test
    void findByCodeNumber_CallsRepositoryMethod_WhenCodeNumberProvided() {
        JourneyTime mockJourneyTime = MockData.getJourneyTime(code);
        when(repo.findByCodeNumber(code)).thenReturn(mockJourneyTime);

        TrafficData data = dataAccessService.findByCodeNumber(code);
        assertThat(data).isNotNull();
        assertThat(data.getSystemCodeNumber()).isEqualTo(code);
        assertThat(data).isInstanceOf(JourneyTime.class);
    }

    @Test
    void findPointDataByCodeNumber_CallsRepositoryMethod_WhenCodeNumberProvided() {
        TrafficIncident mockIncident = MockData.getIncident(code);
        when(repo.findPointDataByCodeNumber(code)).thenReturn(mockIncident);

        TrafficData data = dataAccessService.findPointDataByCodeNumber(code);
        assertThat(data).isNotNull();
        assertThat(data.getSystemCodeNumber()).isEqualTo(code);
        assertThat(data).isInstanceOf(TrafficIncident.class);
    }

}