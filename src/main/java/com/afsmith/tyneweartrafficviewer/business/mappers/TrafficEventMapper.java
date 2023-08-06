package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Defines a mapping from traffic event entities to DTOs.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficEventMapper extends TrafficDataMapper<TrafficEventDTO, TrafficEvent>{

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "planned.startTime", target = "times.startTime")
    @Mapping(source = "planned.endTime", target = "times.endTime")
    @Mapping(source = "eventTypeDescription", target = "typeDescription")
    TrafficEventDTO entityToDto(TrafficEvent event);
}
