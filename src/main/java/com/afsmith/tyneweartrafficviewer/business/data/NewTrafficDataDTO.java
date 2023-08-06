package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.entities.PlannedTimes;
import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * A simplified representation of traffic data being sent in by users wishing to add custom data.
 */
@ToString
@EqualsAndHashCode
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewTrafficDataDTO {
    TrafficDataTypes type;
    String shortDescription;
    String longDescription;
    String locationDescription;
    double latitude;
    double longitude;
    String severityTypeRefDescription;
    String lanesAffectedTypeRefDescription;
    String diversionInForce;
    String phaseTypeRef;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime start;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime end;

    /**
     * Get the location of the new incident as a Point object.
     * @return A point containing the coordinates of the incident.
     */
    public Point getPoint() {
        return new Point(latitude, longitude);
    }

    /**
     * Get the time interval over which the incident occurs.
     * @return The time interval over which the incident occurs.
     */
    public PlannedTimes getTimes() {
        return new PlannedTimes(start, end);
    }

    // Is the provided traffic data valid?
    public boolean isValid() {
        return type != null && getPoint() != null;
    }
}
