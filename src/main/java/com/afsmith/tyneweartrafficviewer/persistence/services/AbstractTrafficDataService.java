package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A data service implementation that allows storage of and access to generic
 * traffic data. Supplied data connectors allow the generic methods to be applied
 * to concrete traffic data types.
 */
@RequiredArgsConstructor
public abstract class AbstractTrafficDataService<T extends TrafficEntity> implements TrafficDataService<T> {
    protected final JpaRepository<T, String> repository;
    protected final Class<T> entityClass;

    /**
     * Get a list of traffic data of the type specified by the connector.
     * @return A list of traffic data DTOs.
     */
    public List<T> listAll() {
        return repository.findAll();
    }

    /**
     * Store a list of traffic data of the type specified by the connector.
     * @param trafficData A list of traffic data.
     */
    public void persistEntities(List<T> trafficData) {
        repository.saveAll(trafficData);
    }

}
