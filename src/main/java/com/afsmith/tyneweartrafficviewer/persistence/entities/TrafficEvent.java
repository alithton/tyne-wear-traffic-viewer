package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * A JPA entity representing traffic event data.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class TrafficEvent extends TrafficData {
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
            String venueName) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription, lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef);
        this.eventTypeDescription = eventTypeDescription;
        this.planned = planned;
        this.organiser = organiser;
        this.venueName = venueName;
    }
}
