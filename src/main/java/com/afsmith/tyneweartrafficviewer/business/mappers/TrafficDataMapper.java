package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.util.List;

/**
 * A generic mapper to provide MapStruct mappings from entities to data transfer
 * objects (DTOs) corresponding to a particular traffic data type.
 * @param <DTO> The data transfer object.
 * @param <E> The data entity.
 */
public interface TrafficDataMapper<DTO extends TrafficDTO, E extends TrafficEntity> {

    /**
     * Given a traffic data entity, get the corresponding DTO.
     * @param entity The entity.
     * @return A data transfer object.
     */
    DTO entityToDto(E entity);

    /**
     * Given a list of traffic data entities, get a corresponding list of DTOs.
     * @param entityList An iterable collection of entities.
     * @return A list of data transfer objects.
     */
    List<DTO> entityToDto(Iterable<E> entityList);
}
