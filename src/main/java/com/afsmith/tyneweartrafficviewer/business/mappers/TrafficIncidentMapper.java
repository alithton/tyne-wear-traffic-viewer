package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper for traffic incidents.
 */
@Mapper(uses = CommentMapper.class)
public interface TrafficIncidentMapper extends TrafficDataMapper<TrafficIncidentDTO, TrafficIncident> { }
