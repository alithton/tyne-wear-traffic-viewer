package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Presents a unified interface for storing and accessing traffic data. This is
 * the intended API by which external classes should interact with the persistence
 * layer.
 */
@Service
@RequiredArgsConstructor
public class TrafficDataPersistence {

    private final TrafficDataServiceIncidents incidentService;
    private final TrafficDataServiceEvent eventService;
    private final TrafficDataServiceAccident accidentService;
    private final TrafficDataServiceRoadworks roadworksService;
    private final TrafficDataServiceJourneyTimes journeyTimeService;
    private final TrafficDataServiceCamera cameraService;
    private final TrafficDataServiceTypicalJourneyTime typicalJourneyTimeService;

    /**
     * Get a list of all the data stored corresponding to the provided traffic
     * data type.
     * @param dataType The traffic data type.
     * @return A list of traffic data.
     */
    public <T extends TrafficEntity> List<T> listAll(TrafficDataTypes dataType) {
        @SuppressWarnings("unchecked")
        var dataService = (TrafficDataService<T>) getDataService(dataType);
        return List.copyOf(dataService.listAll());
    }

    /**
     * Store the provided data in the database.
     * @param trafficData A list of entities to be stored.
     * @param dataType The type of data to be stored.
     */
    public <T extends TrafficEntity> void persistEntities(List<T> trafficData, TrafficDataTypes dataType) {
        @SuppressWarnings("unchecked")
        var dataService = (TrafficDataService<T>) getDataService(dataType);
        dataService.persistEntities(List.copyOf(trafficData));
    }

    public List<TypicalJourneyTime> findTypicalJourneyTimesByTime(LocalTime time, boolean isWeekend) {
        return typicalJourneyTimeService.findByTime(time, isWeekend);
    }

    private TrafficDataService<? extends TrafficEntity> getDataService(TrafficDataTypes dataType) {
        return switch (dataType) {
            case INCIDENT -> incidentService;
            case EVENT -> eventService;
            case ACCIDENT -> accidentService;
            case ROADWORKS -> roadworksService;
            case SPEED -> journeyTimeService;
            case CAMERA -> cameraService;
            case TYPICAL_SPEED -> typicalJourneyTimeService;
        };
    }
}
