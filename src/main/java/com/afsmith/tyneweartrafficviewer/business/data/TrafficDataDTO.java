package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public sealed class TrafficDataDTO permits TrafficEventDTO, TrafficIncidentDTO, TrafficAccidentDTO, TrafficRoadworksDTO {
    String systemCodeNumber;
    TrafficDataTypes type;
    String shortDescription;
    String longDescription;
    String locationDescription;
    PointDTO point;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime creationDate;
    String dataSourceTypeRef;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime confirmedDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime modifiedDate;
    String severityTypeRefDescription;
    String lanesAffectedTypeRefDescription;
    String diversionInForce;
    String phaseTypeRef;
}
