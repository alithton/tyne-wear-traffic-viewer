package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficDataMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A generic connector that supplies the {@link com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataService}
 * with the dependencies it requires to work with a particular concrete data type.
 * This class is intended to be extended by connector classes that provide the necessary
 * information for working with a particular data type.
 * @param <T> The data entity.
 * @param <DTO> The corresponding data transfer object.
 * @param <ID> The type of the ID field, supplied to {@link org.springframework.data.jpa.repository}.
 */
public class TrafficDataTypeConnector<T extends TrafficData, DTO extends TrafficDataDTO, ID> {
    private final JpaRepository<T, ID> repository;
    private final TrafficDataMapper<DTO, T> mapper;
    private final Class<DTO> dtoClass;
    private final Class<T> entityClass;

    protected TrafficDataTypeConnector(
            JpaRepository<T, ID> repository,
            TrafficDataMapper<DTO, T> mapper,
            Class<DTO> dtoClass,
            Class<T> entityClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public JpaRepository<T, ID> getRepository() {
        return repository;
    }

    public TrafficDataMapper<DTO, T> getMapper() {
        return mapper;
    }

    public Class<DTO> getDtoClass() {
        return dtoClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

}
