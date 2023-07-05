package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficRoadwork;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficRoadworkMapper extends TrafficDataMapper<TrafficRoadworksDTO, TrafficRoadwork>{
}
