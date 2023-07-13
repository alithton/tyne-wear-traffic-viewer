package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Entity
public class TrafficAccident extends TrafficPointData {
    String accidentTypeDescription;
    ZonedDateTime accidentTime;
    ZonedDateTime endTime;

    @Builder
    public TrafficAccident(String systemCodeNumber,
                           TrafficDataTypes type,
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
                           String accidentTypeDescription,
                           ZonedDateTime accidentTime,
                           ZonedDateTime endTime) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef);
        this.accidentTypeDescription = accidentTypeDescription;
        this.accidentTime = accidentTime;
        this.endTime = endTime;
    }
}
