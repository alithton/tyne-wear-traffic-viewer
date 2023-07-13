package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficAccidentExternal;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficAccidentExternalMapper extends TrafficDataExternalMapper<TrafficAccidentExternal, TrafficAccident>{
}
