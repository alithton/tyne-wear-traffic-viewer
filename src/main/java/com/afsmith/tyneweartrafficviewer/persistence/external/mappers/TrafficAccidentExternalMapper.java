package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficAccidentExternal;
import org.mapstruct.Mapper;

/**
 * Map traffic accident data received from the Open Data Service to its internal entity
 * representation.
 */
@Mapper
public interface TrafficAccidentExternalMapper extends TrafficDataExternalMapper<TrafficAccidentExternal, TrafficAccident>{
}
