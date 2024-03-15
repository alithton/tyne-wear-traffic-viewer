package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TrafficDataPersistenceTest {

    @InjectMocks
    TrafficDataPersistence dataPersistence;

    @Mock
    TrafficDataAccessServiceImpl dataAccessService;
    @Mock
    ExternalDataAccessService externalDataAccessService;

    @Mock
    TrafficDataServiceJourneyTimes jtDataService;

    @Mock
    TypicalJourneyTimeDataServiceImpl typicalJTDataService;

    List<TrafficIncident> incidents = List.of(MockData.getIncident("code1"),
                                              MockData.getIncident("code2"));

    @Captor
    ArgumentCaptor<List<JourneyTime>> journeyTimeCaptor;

    @Test
    void listAll_ListsDataOfProvidedType_WhenValidTypeProvided() {
        when(dataAccessService.listAll(eq(TrafficIncident.class))).thenReturn(incidents);
        List<TrafficIncident> returnedIncidents = dataPersistence.listAll(TrafficDataTypes.INCIDENT);

        assertThat(returnedIncidents).isNotNull();
        assertThat(returnedIncidents.size()).isEqualTo(2);
    }

    @Test
    void persistEntities_PassesProvidedEntitiesToDataService_WhenListOfEntitiesProvided() {
        List<JourneyTime> journeyTimes = List.of(MockData.getJourneyTime("code1"),
                                                 MockData.getJourneyTime("code2"));

        dataPersistence.persistEntities(journeyTimes, TrafficDataTypes.SPEED);

        verify(jtDataService, times(1)).persistEntities(journeyTimeCaptor.capture());

        List<JourneyTime> capturedJourneyTimes = journeyTimeCaptor.getValue();
        assertThat(capturedJourneyTimes.size()).isEqualTo(2);
    }

    @Test
    void getImage_PassesExternalDataServiceImageURL_WhenCameraCodeNumberProvided() {
        Camera mockCamera = MockData.getCamera("code1");
        when(dataAccessService.findByCodeNumber("code1"))
                .thenReturn(mockCamera);

        dataPersistence.getImage("code1");
        ArgumentCaptor<URL> urlCaptor = ArgumentCaptor.forClass(URL.class);
        verify(externalDataAccessService, times(1)).getImage(urlCaptor.capture());

        assertThat(urlCaptor.getValue()).isEqualTo(mockCamera.getImage());
    }

    @Test
    void getImage_ReturnsNull_WhenCodeNumberNotForCameraData() {
        TrafficData incident = MockData.getIncident("code1");
        when(dataAccessService.findByCodeNumber("code1"))
                .thenReturn(incident);
        byte[] image = dataPersistence.getImage("code1");
        assertThat(image).isNull();
    }

    @Test
    void findTypicalJourneyTimesByTime_CallsDataService_WhenValidDataProvided() {
        when(typicalJTDataService.findByTime(any(LocalTime.class), any(Boolean.class)))
                .thenReturn(List.of(MockData.getTypicalJourneyTime("code1")));


        List<TypicalJourneyTime> typicalJTs = dataPersistence.findTypicalJourneyTimesByTime(LocalTime.NOON, true);
        assertThat(typicalJTs).isNotNull();
        assertThat(typicalJTs.size()).isEqualTo(1);
    }

}