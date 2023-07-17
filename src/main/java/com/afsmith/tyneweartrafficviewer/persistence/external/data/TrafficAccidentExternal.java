package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficAccident;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.TrafficAccidentExternalMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Getter
public final class TrafficAccidentExternal extends TrafficPointDataExternal<TrafficAccident> {
    @JsonIgnore
    TrafficAccidentExternalMapper mapper = Mappers.getMapper(TrafficAccidentExternalMapper.class);
    String accidentTypeDescription;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime accidentTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime endTime;

    @Builder
    public TrafficAccidentExternal(
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
            String accidentTypeDescription,
            ZonedDateTime accidentTime,
            ZonedDateTime endTime) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription,
              point, creationDate, dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription, diversionInForce, phaseTypeRef);
        this.accidentTypeDescription = accidentTypeDescription;
        this.accidentTime = accidentTime;
        this.endTime = endTime;
    }


    @Override
    public TrafficAccident toEntity() {
        return mapper.externalToEntity(this);
    }
}
