package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Defines a mapping from roadwork entities to DTOs.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficRoadworkMapper extends TrafficDataMapper<TrafficRoadworksDTO, TrafficRoadwork>{

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "start", target = "times.startTime")
    @Mapping(source = "end", target = "times.endTime")
    @Mapping(source = "roadworkTypeDescription", target = "typeDescription")
    TrafficRoadworksDTO entityToDto(TrafficRoadwork event);
}
