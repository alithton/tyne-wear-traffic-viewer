package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;

import java.util.List;

/**
 * Defines an interface for storing and accessing traffic data. Implementations
 * of this interface will specify how to perform these actions for particular
 * types of traffic data.
 */
public interface TrafficDataService {

     /**
      * Get a list of all the data managed by the service.
      * @return A list of traffic data.
      */
     List<TrafficDataDTO> listAll();

     /**
      * Store the provided traffic data.
      * @param trafficData A list of traffic data.
      */
     void persist(List<TrafficDataDTO> trafficData);
}
