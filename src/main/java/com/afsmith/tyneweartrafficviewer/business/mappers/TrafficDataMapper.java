package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.util.List;

/**
 * A generic mapper to provide MapStruct mappings between entities and data transfer
 * objects (DTOs) corresponding to a particular traffic data type.
 * @param <DTO> The data transfer object.
 * @param <E> The data entity.
 */
public interface TrafficDataMapper<DTO extends MappableDTO, E extends TrafficEntity> {
    E dtoToEntity(DTO dto);
    List<E> dtoToEntity(Iterable<DTO> dtoList);
    DTO entityToDto(E entity);
    List<DTO> entityToDto(Iterable<E> entityList);
}
