package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficRoadworksExternal;
import org.mapstruct.Mapper;

/**
 * Map roadwork data received from the Open Data Service to its internal entity
 * representation.
 */
@Mapper
public interface TrafficRoadworkExternalMapper extends TrafficDataExternalMapper<TrafficRoadworksExternal, TrafficRoadwork>{
}
