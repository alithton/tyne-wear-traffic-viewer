package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficIncidentExternal;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficIncidentExternalMapper extends TrafficDataExternalMapper<TrafficIncidentExternal, TrafficIncident>{
}
