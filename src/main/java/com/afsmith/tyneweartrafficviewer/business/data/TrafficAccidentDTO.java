package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public final class TrafficAccidentDTO extends TrafficPointDataDTO {
    String accidentTypeDescription;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime accidentTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime endTime;

    @Builder
    public TrafficAccidentDTO(
            String systemCodeNumber,
            TrafficDataTypes type,
            String shortDescription,
            String longDescription,
            String locationDescription,
            PointDTO point,
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
}
