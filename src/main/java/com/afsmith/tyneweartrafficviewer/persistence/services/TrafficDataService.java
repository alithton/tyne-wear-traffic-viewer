package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.util.List;

/**
 * Defines an interface for storing and accessing traffic data. The methods are
 * generic across traffic data types, with a supplied connector providing the
 * necessary dependencies to work with particular concrete data types.
 */
public interface TrafficDataService<T extends TrafficEntity> {

     /**
      * Get a list of all the data managed by the service.
      * @return A list of traffic data.
      */
     List<T> listAll();

     /**
      * Store the provided traffic data in the database.
      * @param trafficData A list of traffic data.
      */
     void persistEntities(List<T> trafficData);

     /**
      * Store a traffic data object in the database.
      * @param trafficData The data to be stored.
      */
     void persist(T trafficData);

     /**
      * Attempt to find traffic data using the system code number.
      * @param codeNumber The code number of the data.
      * @return The traffic data matching the code number.
      */
     T findByCodeNumber(String codeNumber);

     /**
      * Convert provided traffic data to the concrete type T.
      * @param entity The traffic data to be converted.
      * @return Traffic data of concrete type T.
      */
     T convert(TrafficEntity entity);
}
