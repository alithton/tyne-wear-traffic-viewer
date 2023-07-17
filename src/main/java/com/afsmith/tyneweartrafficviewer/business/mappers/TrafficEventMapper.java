package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper for traffic events.
 */
@Mapper
public interface TrafficEventMapper extends TrafficDataMapper<TrafficEventDTO, TrafficEvent>{ }
