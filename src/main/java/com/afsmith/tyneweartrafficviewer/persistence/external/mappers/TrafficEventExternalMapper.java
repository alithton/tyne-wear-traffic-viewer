package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficEventExternal;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficEventExternalMapper extends TrafficDataExternalMapper<TrafficEventExternal, TrafficEvent> {
}
