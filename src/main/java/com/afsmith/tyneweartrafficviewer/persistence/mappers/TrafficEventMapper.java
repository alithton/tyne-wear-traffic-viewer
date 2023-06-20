package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficEventMapper extends TrafficDataMapper<TrafficEventDTO, TrafficEvent>{ }
