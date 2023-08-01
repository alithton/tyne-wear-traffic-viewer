package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * A JPA entity representing traffic incident data.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TrafficIncident extends TrafficPointData {
    private String incidentTypeDescription;
    private ZonedDateTime incidentTime;
    private ZonedDateTime endTime;

    @Builder
    public TrafficIncident(
            String systemCodeNumber,
            TrafficDataTypes type,
            String incidentTypeDescription,
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
            ZonedDateTime incidentTime,
            ZonedDateTime endTime,
            List<Comment> comments,
            User createdBy) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription, lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef, comments, createdBy);
        this.incidentTypeDescription = incidentTypeDescription;
        this.incidentTime = incidentTime;
        this.endTime = endTime;
    }

    public TrafficIncident(TrafficPointData pointData) {
        super(pointData);
    }

    @Override
    public boolean isIncluded(FilterService filter) {
        return filter.filterSeverity(getSeverityTypeRefDescription())
                && filter.filterCustom(this)
                && filter.filterDate(getIncidentTime(), getEndTime());
    }
}
