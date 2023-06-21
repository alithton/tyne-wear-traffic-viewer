package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper for traffic events.
 */
@Mapper
public interface TrafficEventMapper extends TrafficDataMapper<TrafficEventDTO, TrafficEvent>{ }
