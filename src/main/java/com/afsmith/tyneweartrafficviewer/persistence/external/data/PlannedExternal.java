package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

/**
 * Represents a time interval, typically a period over which a traffic incident is active.
 * @param startTime The start time.
 * @param endTime The end time.
 */
public record PlannedExternal(
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime startTime,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime endTime
) {
}
