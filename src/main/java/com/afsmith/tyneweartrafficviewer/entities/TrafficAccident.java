package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
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
                           ZonedDateTime endTime,
                           List<Comment> comments,
                           User createdBy) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef, comments, createdBy);
        this.accidentTypeDescription = accidentTypeDescription;
        this.accidentTime = accidentTime;
        this.endTime = endTime;
    }

    public TrafficAccident(TrafficPointData pointData) {
        super(pointData);
    }

    @Override
    public boolean isIncluded(FilterService filter) {
        return filter.filterSeverity(getSeverityTypeRefDescription())
                && filter.filterCustom(this)
                && filter.filterDate(getAccidentTime(), getEndTime());
    }
}
