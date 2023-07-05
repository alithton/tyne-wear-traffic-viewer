package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficRoadworksExternal;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficRoadworkExternalMapper extends TrafficDataExternalMapper<TrafficRoadworksExternal, TrafficRoadwork>{
}
