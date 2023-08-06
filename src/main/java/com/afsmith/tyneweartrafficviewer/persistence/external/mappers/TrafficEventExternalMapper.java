package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficEventExternal;
import org.mapstruct.Mapper;

/**
 * Map traffic event data received from the Open Data Service to its internal entity
 * representation.
 */
@Mapper
public interface TrafficEventExternalMapper extends TrafficDataExternalMapper<TrafficEventExternal, TrafficEvent> {
}
