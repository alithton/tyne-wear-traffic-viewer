package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /**
     * Get a list of all the data stored corresponding to the provided traffic
     * data type.
     * @param dataType The traffic data type.
     * @return A list of traffic data.
     */
    public List<TrafficDataDTO> listAll(TrafficDataTypes dataType) {
        var dataService = getDataService(dataType);
        return List.copyOf(dataService.listAll());
    }

    /**
     * Store the provided traffic data in the repository. Only data of the specified
     * type will be stored.
     * @param trafficData The data to be stored.
     * @param dataType The type of data to be stored.
     */
    public void persist(List<TrafficDataDTO> trafficData, TrafficDataTypes dataType) {
        var dataService = getDataService(dataType);
        dataService.persist(trafficData);
    }

    /**
     * Store the provided data in the database.
     * @param trafficData A list of entities to be stored.
     * @param dataType The type of data to be stored.
     */
    public void persistEntities(List<TrafficData> trafficData, TrafficDataTypes dataType) {
        var dataService = getDataService(dataType);
        dataService.persistEntities(trafficData);
    }

    private TrafficDataService<? extends TrafficData, ? extends TrafficDataDTO, String> getDataService(TrafficDataTypes dataType) {
        return switch (dataType) {
            case INCIDENT -> incidentService;
            case EVENT -> eventService;
            case ACCIDENT -> accidentService;
            case ROADWORKS -> roadworksService;
            case SPEED -> journeyTimeService;
            case CAMERA -> cameraService;
        };
    }
}
