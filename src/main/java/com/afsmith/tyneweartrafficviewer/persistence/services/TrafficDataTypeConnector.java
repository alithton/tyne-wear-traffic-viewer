package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficDataEntity;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficDataMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

/**
 * A generic connector that supplies the {@link com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataService}
 * with the dependencies it requires to work with a particular concrete data type.
 * This class is intended to be extended by connector classes that provide the necessary
 * information for working with a particular data type.
 * @param <T> The data entity.
 * @param <DTO> The corresponding data transfer object.
 * @param <ID> The type of the ID field, supplied to {@link org.springframework.data.jpa.repository}.
 */
public class TrafficDataTypeConnector<T extends TrafficDataEntity, DTO extends TrafficDataDTO, ID> {
    private final JpaRepository<T, ID> repository;
    private final TrafficDataMapper<DTO, T> mapper;
    private final Class<DTO> dtoClass;

    protected TrafficDataTypeConnector(
            JpaRepository<T, ID> repository,
            TrafficDataMapper<DTO, T> mapper,
            Class<DTO> dtoClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.dtoClass = dtoClass;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TrafficDataTypeConnector) obj;
        return Objects.equals(this.repository, that.repository) &&
                Objects.equals(this.mapper, that.mapper) &&
                Objects.equals(this.dtoClass, that.dtoClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, mapper, dtoClass);
    }

    @Override
    public String toString() {
        return "TrafficDataTypeAdapter[" +
                "repository=" + repository + ", " +
                "mapper=" + mapper + ", " +
                "dtoClass=" + dtoClass + ']';
    }
}
