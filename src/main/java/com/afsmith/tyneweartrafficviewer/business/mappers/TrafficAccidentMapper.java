package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CommentMapper.class)
public interface TrafficAccidentMapper extends TrafficDataMapper<TrafficAccidentDTO, TrafficAccident> {

    @Override
    @Mapping(source = "accidentTime", target = "times.startTime")
    @Mapping(source = "endTime", target = "times.endTime")
    @Mapping(source = "accidentTypeDescription", target = "typeDescription")
    TrafficAccidentDTO entityToDto(TrafficAccident accident);
}
