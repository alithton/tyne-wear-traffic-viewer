package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
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
    private final TrafficPointDataService pointDataService;
    private final ExternalDataAccessService dataAccessService;

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

    /**
     * Store the provided data in the database.
     * @param trafficData The data to be stored.
     * @param dataType The type of data to be stored.
     */
    public <T extends TrafficEntity> void persist(T trafficData,  TrafficDataTypes dataType) {
        @SuppressWarnings("unchecked")
        var dataService = (TrafficDataService<T>) getDataService(dataType);
        dataService.persist(trafficData);
    }

    /**
     * Find traffic data of the specified type that matches the provided code number.
     * @param codeNumber The system code number to search for.
     * @param dataType The type of data to search.
     * @return The located traffic data, or null.
     */
    public <T extends TrafficEntity> T find(String codeNumber, TrafficDataTypes dataType) {
        @SuppressWarnings("unchecked")
        var dataService = (TrafficDataService<T>) getDataService(dataType);
        return dataService.findByCodeNumber(codeNumber);
    }

    /**
     * Find traffic point data matching the provided code number.
     * @param codeNumber The system code number to search for.
     * @return Return the traffic data, if found, or else null.
     */
    public <T extends TrafficPointData> T find(String codeNumber) {
        TrafficPointData pointData = pointDataService.findByCodeNumber(codeNumber);
        TrafficDataTypes dataType = pointData.getType();
        @SuppressWarnings("unchecked")
        var dataService = (TrafficDataService<T>) getDataService(dataType);
        return dataService.convert(pointData);
    }

    /**
     * Save a comment that is being added to the provided point data.
     * @param pointData The traffic data to which the comment is being added.
     * @param comment The comment being added.
     */
    public void saveComment(TrafficPointData pointData, Comment comment) {
        TrafficDataTypes dataType = pointData.getType();
        persist(pointData, dataType);
        pointDataService.saveComment(comment);
    }

    /**
     * List all typical journey time data for the specified time. Data is binned
     * into 5 minute intervals so the provided time will be rounded to the nearest
     * 5 minutes. Data is available for either weekdays or the weekend.
     * @param time The time of day for which to retrieve data.
     * @param isWeekend Should weekend data be retrieved? If false, will return
     *                  data for weekdays.
     * @return A list of typical journey time data for the requested time and day.
     */
    public List<TypicalJourneyTime> findTypicalJourneyTimesByTime(LocalTime time, boolean isWeekend) {
        return typicalJourneyTimeService.findByTime(time, isWeekend);
    }

    /**
     * Get the most recent image from the traffic camera specified by the system
     * code number.
     * @param systemCodeNumber The ID of the requested camera.
     * @return The image as an array of bytes.
     */
    public byte[] getImage(String systemCodeNumber) {
        Camera camera = cameraService.findById(systemCodeNumber);
        URL imageUrl = camera.getImage();
        return dataAccessService.getImage(imageUrl);
    }

    // Get the appropriate data service for the requested data type.
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
