package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record PlannedDTO(
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime startTime,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime endTime
) {
}
