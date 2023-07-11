package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

import java.util.List;

/**
 * A data service implementation that allows storage of and access to generic
 * traffic data. Supplied data connectors allow the generic methods to be applied
 * to concrete traffic data types.
 */
@RequiredArgsConstructor
public abstract class AbstractTrafficDataService<T extends TrafficData, DTO extends TrafficDataDTO, ID> implements TrafficDataService<T, DTO, ID> {
    protected final TrafficDataMapper<DTO, T> mapper;
    protected final JpaRepository<T, ID> repository;
    protected final Class<DTO> dtoClass;
    protected final Class<T> entityClass;

    /**
     * Get a list of traffic data of the type specified by the connector.
     * @return A list of traffic data DTOs.
     */
    public List<DTO> listAll() {
        List<T> entities = repository.findAll();
        return List.copyOf(mapper.entityToDto(entities));
    }

    /**
     * Store a list of traffic data of the type specified by the connector.
     * @param trafficData A list of traffic data.
     */
    public void persist(List<TrafficDataDTO> trafficData) {
        List<DTO> validatedDtos = downcastList(trafficData, dtoClass);
        List<T> entities = mapper.dtoToEntity(validatedDtos);
        repository.saveAll(entities);
    }

    public void persistEntities(List<TrafficData> trafficData) {
        List<T> validatedEntities = downcastList(trafficData, entityClass);
        repository.saveAll(validatedEntities);
    }

}
