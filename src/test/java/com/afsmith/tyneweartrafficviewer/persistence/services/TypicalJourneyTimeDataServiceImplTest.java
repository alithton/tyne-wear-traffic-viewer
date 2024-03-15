package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TypicalJourneyTimeRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


@ExtendWith(MockitoExtension.class)
class TypicalJourneyTimeDataServiceImplTest {

    @InjectMocks
    TypicalJourneyTimeDataServiceImpl dataService;

    @Mock
    TypicalJourneyTimeRepository repository;

    @Test
    void findByTime_CallsRepositoryMethod_WhenCalled() {
        when(repository.findByTimeAndWeekend(any(LocalTime.class), any(Boolean.class)))
                .thenReturn(List.of(MockData.getTypicalJourneyTime("1")));

        List<TypicalJourneyTime> typicalJTs = dataService.findByTime(LocalTime.NOON, false);
        assertThat(typicalJTs).isNotNull();
        assertThat(typicalJTs.size()).isEqualTo(1);
    }
}