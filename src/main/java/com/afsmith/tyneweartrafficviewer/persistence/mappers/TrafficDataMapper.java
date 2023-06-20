package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import java.util.List;

public interface TrafficDataMapper<DTO, E> {
    E dtoToEntity(DTO dto);
    List<E> dtoToEntity(Iterable<DTO> dtoList);
    DTO entityToDto(E entity);
    List<DTO> entityToDto(Iterable<E> entityList);
}
