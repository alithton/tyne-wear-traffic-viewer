package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.business.mappers.TrafficDataMapper;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractDataConversionService<T extends TrafficEntity, DTO extends MappableDTO> implements DataConversionService<T, DTO> {

    private final TrafficDataMapper<DTO, T> mapper;

    public List<T> dtoToEntity(List<DTO> dtos) {
        return mapper.dtoToEntity(dtos);
    }

    public List<DTO> entityToDto(List<T> entities) {
        return mapper.entityToDto(entities);
    }

}
