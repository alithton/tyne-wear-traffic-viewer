package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * A MapStruct mapper for traffic events.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficEventMapper extends TrafficDataMapper<TrafficEventDTO, TrafficEvent>{
    @Override
    @Mapping(source = "planned.startTime", target = "times.startTime")
    @Mapping(source = "planned.endTime", target = "times.endTime")
    @Mapping(source = "eventTypeDescription", target = "typeDescription")
    TrafficEventDTO entityToDto(TrafficEvent event);
}
