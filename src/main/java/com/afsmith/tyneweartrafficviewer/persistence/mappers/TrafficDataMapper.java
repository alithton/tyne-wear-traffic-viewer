package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;

import java.util.List;

/**
 * A generic mapper to provide MapStruct mappings between entities and data transfer
 * objects (DTOs) corresponding to a particular traffic data type.
 * @param <DTO> The data transfer object.
 * @param <E> The data entity.
 */
public interface TrafficDataMapper<DTO extends TrafficDataDTO, E extends TrafficData> {
    E dtoToEntity(DTO dto);
    List<E> dtoToEntity(Iterable<DTO> dtoList);
    DTO entityToDto(E entity);
    List<DTO> entityToDto(Iterable<E> entityList);
}
