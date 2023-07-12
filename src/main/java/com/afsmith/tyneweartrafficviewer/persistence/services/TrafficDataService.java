package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;

import java.util.List;

/**
 * Defines an interface for storing and accessing traffic data. The methods are
 * generic across traffic data types, with a supplied connector providing the
 * necessary dependencies to work with particular concrete data types.
 */
public interface TrafficDataService<T extends TrafficData, DTO extends TrafficDataDTO, ID> {

     /**
      * Get a list of all the data managed by the service.
      * @return A list of traffic data.
      */
     List<DTO> listAll();

     /**
      * Store the provided traffic data in the database.
      * @param trafficData A list of traffic data.
      */
     void persist(List<TrafficDataDTO> trafficData);

     void persistEntities(List<TrafficData> trafficData);
}
