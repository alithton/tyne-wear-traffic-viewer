package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrafficIncident {
    @Id
    private String systemCodeNumber;
    private TrafficDataTypes type;
    private String incidentTypeDescription;
    private String shortDescription;
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
    private ZonedDateTime incidentTime;
    private ZonedDateTime endTime;
}
