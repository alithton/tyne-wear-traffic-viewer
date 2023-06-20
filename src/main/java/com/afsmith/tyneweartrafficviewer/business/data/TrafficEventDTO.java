package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record TrafficEventDTO(
        String systemCodeNumber,
        TrafficDataTypes type,
        String eventTypeDescription,
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
        PlannedDTO planned,
        String organiser,
        String venueName
) implements TrafficDataDTO { }
