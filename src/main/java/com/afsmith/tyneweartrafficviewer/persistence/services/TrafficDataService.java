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
}
