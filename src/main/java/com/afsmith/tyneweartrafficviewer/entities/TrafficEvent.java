package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * A JPA entity representing traffic event data.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class TrafficEvent extends TrafficPointData {
    String eventTypeDescription;
    PlannedTimes planned;
    String organiser;
    String venueName;

    @Builder
    public TrafficEvent(
            String systemCodeNumber,
            TrafficDataTypes type,
            String eventTypeDescription,
            String shortDescription,
            String longDescription,
            String locationDescription,
            Point point,
            ZonedDateTime creationDate,
            String dataSourceTypeRef,
            ZonedDateTime confirmedDate,
            ZonedDateTime modifiedDate,
            String severityTypeRefDescription,
            String lanesAffectedTypeRefDescription,
            String diversionInForce,
            String phaseTypeRef,
            PlannedTimes planned,
            String organiser,
            String venueName,
            List<Comment> comments) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription, lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef, comments);
        this.eventTypeDescription = eventTypeDescription;
        this.planned = planned;
        this.organiser = organiser;
        this.venueName = venueName;
    }
}
