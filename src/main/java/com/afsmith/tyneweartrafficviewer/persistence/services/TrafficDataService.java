package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficDataEntity;

import java.util.List;

/**
 * Defines an interface for storing and accessing traffic data. The methods are
 * generic across traffic data types, with a supplied connector providing the
 * necessary dependencies to work with particular concrete data types.
 */
public interface TrafficDataService {

     /**
      * Get a list of all the data managed by the service.
      * @param connector The connector for the traffic data type.
      * @return A list of traffic data.
      */
     <T extends TrafficDataEntity, DTO extends TrafficDataDTO, ID> List<TrafficDataDTO> listAll(TrafficDataTypeConnector<T, DTO, ID> connector);

     /**
      * Store the provided traffic data in the database.
      * @param trafficData A list of traffic data.
      * @param connector The connector for the traffic data type.
      */
     <T extends TrafficDataEntity, DTO extends TrafficDataDTO, ID> void persist(List<TrafficDataDTO> trafficData, TrafficDataTypeConnector<T, DTO, ID> connector);
}
