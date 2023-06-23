package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * A JPA entity representing traffic event data.
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrafficEvent extends TrafficDataEntity {
    @Id
    String systemCodeNumber;
    TrafficDataTypes type;
    String eventTypeDescription;
    String shortDescription;
    @Column(columnDefinition = "TEXT")
    String longDescription;
    String locationDescription;
    Point point;
    ZonedDateTime creationDate;
    String dataSourceTypeRef;
    ZonedDateTime confirmedDate;
    ZonedDateTime modifiedDate;
    String severityTypeRefDescription;
    String lanesAffectedTypeRefDescription;
    String diversionInForce;
    String phaseTypeRef;
    PlannedTimes planned;
    String organiser;
    String venueName;
}
