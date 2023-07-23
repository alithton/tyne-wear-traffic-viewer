package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import org.mapstruct.Mapper;

@Mapper(uses = CommentMapper.class)
public interface TrafficRoadworkMapper extends TrafficDataMapper<TrafficRoadworksDTO, TrafficRoadwork>{
}
