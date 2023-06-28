package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficAccidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficAccident;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficAccidentMapper extends TrafficDataMapper<TrafficAccidentDTO, TrafficAccident> {
}
