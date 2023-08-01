package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * A MapStruct mapper for traffic incidents.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficIncidentMapper extends TrafficDataMapper<TrafficIncidentDTO, TrafficIncident> {
    @Override
    @Mapping(source = "incidentTime", target = "times.startTime")
    @Mapping(source = "endTime", target = "times.endTime")
    @Mapping(source = "incidentTypeDescription", target = "typeDescription")
    TrafficIncidentDTO entityToDto(TrafficIncident incident);
}
