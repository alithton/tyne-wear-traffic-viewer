package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficIncidentExternal;
import org.mapstruct.Mapper;

/**
 * Map traffic incident data received from the Open Data Service to its internal entity
 * representation.
 */
@Mapper
public interface TrafficIncidentExternalMapper extends TrafficDataExternalMapper<TrafficIncidentExternal, TrafficIncident>{
}
