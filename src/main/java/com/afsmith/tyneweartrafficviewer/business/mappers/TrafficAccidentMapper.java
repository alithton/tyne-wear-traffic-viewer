package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficAccidentMapper extends TrafficDataMapper<TrafficAccidentDTO, TrafficAccident> {
}
