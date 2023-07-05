package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.TrafficIncidentExternalMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Getter
public final class TrafficIncidentExternal extends TrafficPointDataExternal<TrafficIncident> {
        @JsonIgnore
        private final TrafficIncidentExternalMapper mapper = Mappers.getMapper(TrafficIncidentExternalMapper.class);
        String incidentTypeDescription;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime incidentTime;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime endTime;

        @Builder
        public TrafficIncidentExternal(
                String systemCodeNumber,
                TrafficDataTypes type,
                String shortDescription,
                String longDescription,
                String locationDescription,
                PointExternal point,
                ZonedDateTime creationDate,
                String dataSourceTypeRef,
                ZonedDateTime confirmedDate,
                ZonedDateTime modifiedDate,
                String severityTypeRefDescription,
                String lanesAffectedTypeRefDescription,
                String diversionInForce,
                String phaseTypeRef,
                String incidentTypeDescription,
                ZonedDateTime incidentTime,
                ZonedDateTime endTime) {
                super(systemCodeNumber, type, shortDescription, longDescription, locationDescription,
                      point, creationDate, dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
                      lanesAffectedTypeRefDescription, diversionInForce, phaseTypeRef);
                this.incidentTypeDescription = incidentTypeDescription;
                this.incidentTime = incidentTime;
                this.endTime = endTime;
        }


        @Override
        public TrafficIncident toEntity() {
                return mapper.externalToEntity(this);
        }
}



