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
     * Get a list of traffic data.
     * @return A list of traffic data DTOs.
     */
    public List<T> listAll() {
        return repository.findAll();
    }

    /**
     * Find the traffic data specified by the provided system code number. If no
     * matches are found, return null.
     * @param codeNumber The system code number to search for.
     * @return The located traffic data or null.
     */
    public T findByCodeNumber(String codeNumber) {
        return repository.findById(codeNumber)
                         .orElse(null);
    }

    /**
     * Store an item of traffic data in the database.
     * @param trafficData The traffic data to be stored.
     */
    public void persist(T trafficData) {
        repository.save(trafficData);
    }

    /**
     * Store a list of traffic data.
     * @param trafficData A list of traffic data.
     */
    public void persistEntities(List<T> trafficData) {
        repository.saveAll(trafficData);
    }

    /**
     * Convert the provided traffic data to the concrete type T, if possible.
     * @param entity The traffic data entity.
     * @return If the provided entity is an instance of T, then a traffic data
     * object of type T is returned. Otherwise, null.
     */
    @SuppressWarnings("unchecked")
    public T convert(TrafficEntity entity) {
        T converted = null;
        if (entityClass.isInstance(entity)) {
            // The cast is safe as it can only happen if entity is an instance of T.
            converted = (T) entity;
        }
        return converted;
    }

}
