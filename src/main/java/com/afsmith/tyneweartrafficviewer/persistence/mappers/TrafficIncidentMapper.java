package com.afsmith.tyneweartrafficviewer.persistence.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TrafficIncidentMapper {
    TrafficIncident dtoToEntity(TrafficIncidentDTO dto);
    List<TrafficIncident> dtoToEntity(Iterable<TrafficIncidentDTO> dtoList);
    TrafficIncidentDTO entityToDto(TrafficIncident entity);
    List<TrafficIncidentDTO> entityToDto(Iterable<TrafficIncident> entityList);
}
