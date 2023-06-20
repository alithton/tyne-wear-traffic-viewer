package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Presents a unified interface for storing and accessing traffic data.
 */
@Service
@RequiredArgsConstructor
public class TrafficDataPersistence {
    @Qualifier("incidentService")
    private final TrafficDataService incidentService;
    @Qualifier("eventsService")
    private final TrafficDataService eventsService;

    /**
     * Get a list of all the data stored corresponding to the provided traffic
     * data type.
     * @param dataType The traffic data type.
     * @return A list of traffic data.
     */
    public List<TrafficDataDTO> listAll(TrafficDataTypes dataType) {
        return switch (dataType) {
            case INCIDENT -> incidentService.listAll();
            case EVENT -> eventsService.listAll();
            default -> null;
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
            case INCIDENT -> incidentService.persist(trafficData);
            case EVENT -> eventsService.persist(trafficData);
            default -> System.out.println("Default");
        }
    }
}
