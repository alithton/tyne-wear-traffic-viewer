package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.util.List;

public interface DataConversionService<T extends TrafficEntity, DTO extends MappableDTO> {
    List<T> dtoToEntity(List<DTO> dtos);
    List<DTO> entityToDto(List<T> entities);
}
