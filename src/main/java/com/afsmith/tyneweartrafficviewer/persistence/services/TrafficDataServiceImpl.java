package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

/**
 * A data service implementation that allows storage of and access to generic
 * traffic data. Supplied data connectors allow the generic methods to be applied
 * to concrete traffic data types.
 */
@Service
public class TrafficDataServiceImpl implements TrafficDataService {

    /**
     * Get a list of traffic data of the type specified by the connector.
     * @param connector The connector for the traffic data type.
     * @return A list of traffic data DTOs.
     */
    @Override
    public <T extends TrafficData, DTO extends TrafficDataDTO, ID> List<TrafficDataDTO> listAll(TrafficDataTypeConnector<T, DTO, ID> connector) {
        List<T> entities = connector.getRepository().findAll();
        return List.copyOf(connector.getMapper().entityToDto(entities));
    }

    /**
     * Store a list of traffic data of the type specified by the connector.
     * @param trafficData A list of traffic data.
     * @param connector The connector for the traffic data type.
     */
    @Override
    public <T extends TrafficData, DTO extends TrafficDataDTO, ID> void persist(List<TrafficDataDTO> trafficData, TrafficDataTypeConnector<T, DTO, ID> connector) {
        List<DTO> eventDTOS = downcastList(trafficData, connector.getDtoClass());
        List<T> events = connector.getMapper().dtoToEntity(eventDTOS);
        connector.getRepository().saveAll(events);
    }

    @Override
    public <T extends TrafficData, DTO extends TrafficDataDTO, ID> void persistEntities(List<TrafficData> trafficData, TrafficDataTypeConnector<T, DTO, ID> connector) {
        List<T> validatedData = downcastList(trafficData, connector.getEntityClass());
        connector.getRepository().saveAll(validatedData);
    }


}
