package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

/**
 * A data service implementation that allows storage of and access to generic
 * traffic data. Supplied data connectors allow the generic methods to be applied
 * to concrete traffic data types.
 */
@RequiredArgsConstructor
@Service
public class TrafficDataServiceImpl implements TrafficDataService {
    private final RoutingService routingService;

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
        List<DTO> dtos = downcastList(trafficData, connector.getDtoClass());
        List<T> entities = connector.getMapper().dtoToEntity(dtos);
        connector.getRepository().saveAll(entities);
    }

    @Override
    public <T extends TrafficData, DTO extends TrafficDataDTO, ID> void persistEntities(List<TrafficData> trafficData, TrafficDataTypeConnector<T, DTO, ID> connector) {
        List<T> validatedData = downcastList(trafficData, connector.getEntityClass());

        if (connector.getEntityClass() == JourneyTime.class) {
            validatedData.forEach(e -> loadRoute((JourneyTime) e));
        }

        connector.getRepository().saveAll(validatedData);
    }

    private void loadRoute(JourneyTime journeyTime) {

        // Avoid re-calculating existing routes.
        if (journeyTime.getRoute() != null) return;

        SimpleRoute route = routingService.calculateRoute(journeyTime.getPoint(), journeyTime.getEndPoint());
        journeyTime.setRoute(route);
    }

}
