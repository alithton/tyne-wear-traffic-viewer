package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * The base traffic data class that expresses the shared relationship between
 * all traffic data entities.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class TrafficData {
    @Id
    private String systemCodeNumber;
    private TrafficDataTypes type;
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    private String locationDescription;
    private Point point;
    private ZonedDateTime creationDate;
    private String dataSourceTypeRef;
    private ZonedDateTime confirmedDate;
    private ZonedDateTime modifiedDate;
    private String severityTypeRefDescription;
    private String lanesAffectedTypeRefDescription;
    private String diversionInForce;
    private String phaseTypeRef;
}
