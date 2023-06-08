package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record TrafficIncidentDTO(
        String systemCodeNumber,
        String type,
        String incidentTypeDescription,
        String shortDescription,
        String longDescription,
        String locationDescription,
        PointDTO point,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime creationDate,
        String dataSourceTypeRef,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime confirmedDate,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime modifiedDate,
        String severityTypeRefDescription,
        String lanesAffectedTypeRefDescription,
        String diversionInForce,
        String phaseTypeRef,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime incidentTime,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime endTime
) implements TrafficDataDTO { }
