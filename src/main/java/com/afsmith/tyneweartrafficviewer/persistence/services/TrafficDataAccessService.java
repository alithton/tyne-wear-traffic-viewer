package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;

import java.util.List;

/**
 * Defines an interface for storing and accessing traffic data. The methods are
 * generic across traffic data types, with a supplied connector providing the
 * necessary dependencies to work with particular concrete data types.
 */
public interface TrafficDataAccessService {

    /**
     * Get a list of all the data managed by the service of the specified type.
     * @param dataType The type of traffic data to be retrieved.
     * @return A list of traffic data.
     */
    <T extends TrafficData> List<T> listAll(Class<T> dataType);

    /**
     * Store the provided traffic data in the database.
     * @param trafficData A list of traffic data.
     */
    void saveAll(List<? extends TrafficData> trafficData);

    /**
     * Store the provided traffic data in the database.
     * @param trafficData A list of traffic data.
     */
    void save(TrafficData trafficData);

    /**
     * Attempt to find traffic data using the system code number.
     * @param systemCodeNumber The code number of the data.
     * @return The traffic data matching the code number.
     */
    <T extends TrafficData> T findByCodeNumber(String systemCodeNumber);

    /**
     * Attempt to find point traffic data using the system code number.
     * @param systemCodeNumber The code number of the data.
     * @return The traffic data matching the code number.
     */
    <T extends TrafficPointData> T findPointDataByCodeNumber(String systemCodeNumber);
}
