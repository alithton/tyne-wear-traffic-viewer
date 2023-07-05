package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import org.mapstruct.Mapper;

@Mapper
public interface JourneyTimeMapper extends TrafficDataMapper<JourneyTimeDTO, JourneyTime>{
}
