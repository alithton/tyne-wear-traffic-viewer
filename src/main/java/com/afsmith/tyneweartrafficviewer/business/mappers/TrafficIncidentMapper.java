package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Defines a mapping from traffic incident entities to DTOs.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficIncidentMapper extends TrafficDataMapper<TrafficIncidentDTO, TrafficIncident> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "incidentTime", target = "times.startTime")
    @Mapping(source = "endTime", target = "times.endTime")
    @Mapping(source = "incidentTypeDescription", target = "typeDescription")
    TrafficIncidentDTO entityToDto(TrafficIncident incident);
}
