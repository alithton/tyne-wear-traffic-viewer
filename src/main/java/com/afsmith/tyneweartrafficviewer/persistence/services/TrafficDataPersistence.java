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

    private final TrafficIncidentConnector incidentConnector;
    private final TrafficEventConnector eventConnector;
    private final TrafficAccidentConnector accidentConnector;
    private final TrafficRoadworkConnector roadworkConnector;
    private final JourneyTimeConnector journeyTimeConnector;
    private final CameraConnector cameraConnector;
    private final TrafficDataService dataService;

    /**
     * Get a list of all the data stored corresponding to the provided traffic
     * data type.
     * @param dataType The traffic data type.
     * @return A list of traffic data.
     */
    public List<TrafficDataDTO> listAll(TrafficDataTypes dataType) {
        return switch (dataType) {
            case INCIDENT -> dataService.listAll(incidentConnector);
            case EVENT -> dataService.listAll(eventConnector);
            case ACCIDENT -> dataService.listAll(accidentConnector);
            case ROADWORKS -> dataService.listAll(roadworkConnector);
            case SPEED -> dataService.listAll(journeyTimeConnector);
            case CAMERA -> dataService.listAll(cameraConnector);
        };
    }

    /**
     * Store the provided traffic data in the repository. Only data of the specified
     * type will be stored.
     * @param trafficData The data to be stored.
     * @param dataType The type of data to be stored.
     */
    public void persist(List<TrafficDataDTO> trafficData, TrafficDataTypes dataType) {
        switch (dataType) {
            case INCIDENT -> dataService.persist(trafficData, incidentConnector);
            case EVENT -> dataService.persist(trafficData, eventConnector);
            case ACCIDENT -> dataService.persist(trafficData, accidentConnector);
            case ROADWORKS -> dataService.persist(trafficData, roadworkConnector);
            case SPEED -> dataService.persist(trafficData, journeyTimeConnector);
            case CAMERA -> dataService.persist(trafficData, cameraConnector);
        }
    }

    /**
     * Store the provided data in the database.
     * @param trafficData A list of entities to be stored.
     * @param dataType The type of data to be stored.
     */
    public void persistEntities(List<TrafficData> trafficData, TrafficDataTypes dataType) {
        switch (dataType) {
            case INCIDENT -> dataService.persistEntities(trafficData, incidentConnector);
            case EVENT -> dataService.persistEntities(trafficData, eventConnector);
            case ACCIDENT -> dataService.persistEntities(trafficData, accidentConnector);
            case ROADWORKS -> dataService.persistEntities(trafficData, roadworkConnector);
            case SPEED -> dataService.persistEntities(trafficData, journeyTimeConnector);
            case CAMERA -> dataService.persistEntities(trafficData, cameraConnector);
        }
    }
}
