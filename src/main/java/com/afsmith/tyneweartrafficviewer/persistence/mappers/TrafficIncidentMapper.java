package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper for traffic incidents.
 */
@Mapper
public interface TrafficIncidentMapper extends TrafficDataMapper<TrafficIncidentDTO, TrafficIncident> { }
