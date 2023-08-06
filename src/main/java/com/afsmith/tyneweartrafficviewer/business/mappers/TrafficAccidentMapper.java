package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Defines a mapping from traffic accident entities to DTOs.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficAccidentMapper extends TrafficDataMapper<TrafficAccidentDTO, TrafficAccident> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "accidentTime", target = "times.startTime")
    @Mapping(source = "endTime", target = "times.endTime")
    @Mapping(source = "accidentTypeDescription", target = "typeDescription")
    TrafficAccidentDTO entityToDto(TrafficAccident accident);
}
