package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.*;
import com.afsmith.tyneweartrafficviewer.exceptions.DataNotFoundException;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;

/**
 * Presents a unified interface for storing and accessing traffic data of any
 * supported type.
 */
@Service
@RequiredArgsConstructor
public class TrafficDataPersistence {
    private final ExternalDataAccessService externalDataAccessService;
    private final TrafficDataAccessService trafficDataService;
    private final TypicalJourneyTimeDataService typicalJTDataService;
    private final TrafficDataServiceJourneyTimes jtDataService;

    /**
     * Get a list of all the data stored corresponding to the provided traffic
     * data type.
     * @param dataType The traffic data type.
     * @return A list of traffic data.
     */
    public <T extends TrafficData> List<T> listAll(TrafficDataTypes dataType) {
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) dataType.getEntityClass();
        return trafficDataService.listAll(entityClass);
    }

    /**
     * Store the provided data in the database.
     * @param trafficData A list of entities to be stored.
     * @param dataType The type of data to be stored.
     */
    @SuppressWarnings("unchecked")
    public <T extends TrafficData> void persistEntities(List<T> trafficData, TrafficDataTypes dataType) {
        if (dataType == TrafficDataTypes.SPEED) {
            jtDataService.persistEntities( (List<JourneyTime>) trafficData);
        } else {
            trafficDataService.saveAll(trafficData);
        }
    }

    /**
     * Store the provided data in the database.
     * @param trafficData The data to be stored.
     * @param dataType The type of data to be stored.
     */
    public <T extends TrafficData> void persist(T trafficData,  TrafficDataTypes dataType) {
        if (dataType == TrafficDataTypes.SPEED) {
            jtDataService.persistEntities(List.of( (JourneyTime) trafficData));
        } else {
            trafficDataService.save(trafficData);
        }
    }

    /**
     * Find traffic data of the specified type that matches the provided code number.
     * @param codeNumber The system code number to search for.
     * @param dataType The type of data to search.
     * @return The located traffic data, or null.
     */
    public <T extends TrafficData> T find(String codeNumber, TrafficDataTypes dataType) {
        return trafficDataService.findByCodeNumber(codeNumber);
    }

    /**
     * Find traffic point data matching the provided code number.
     * @param codeNumber The system code number to search for.
     * @return Return the traffic data, if found, or else null.
     */
    public <T extends TrafficPointData> T find(String codeNumber) throws DataNotFoundException {
        return trafficDataService.findPointDataByCodeNumber(codeNumber);
    }

    /**
     * Save a comment that is being added to the provided point data.
     * @param pointData The traffic data to which the comment is being added.
     * @param comment The comment being added.
     */
    public <T extends TrafficPointData> void saveComment(T pointData, Comment comment) {
        pointData.addComment(comment);
        trafficDataService.save(pointData);
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
        return typicalJTDataService.findByTime(time, isWeekend);
    }

    /**
     * Get the most recent image from the traffic camera specified by the system
     * code number.
     * @param systemCodeNumber The ID of the requested camera.
     * @return The image as an array of bytes.
     */
    public byte[] getImage(String systemCodeNumber) {
        TrafficData data = trafficDataService.findByCodeNumber(systemCodeNumber);
        Camera camera;
        if (data instanceof Camera) {
            camera = (Camera) data;
        } else {
            return null;
        }
        URL imageUrl = camera.getImage();
        return externalDataAccessService.getImage(imageUrl);
    }

}
