package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents traffic incident data.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TrafficIncident extends TrafficPointData {
    private String incidentTypeDescription;
    private ZonedDateTime incidentTime;
    private ZonedDateTime endTime;

    /**
     * An all-arguments constructor for traffic incident data.
     */
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

    /**
     * A copy constructor for traffic incident data.
     * @param pointData The traffic data from which the incident data should be constructed.
     */
    public TrafficIncident(TrafficPointData pointData) {
        super(pointData);
    }

    /**
     * Should the entity be included in the returned set of data based on the criteria
     * specified by the filter service?
     * @param filter A service providing a set of configurable filters to determine
     *                      which entities should be included in the server response.
     * @return Whether the entity should be included.
     */
    @Override
    public boolean isIncluded(FilterService filter) {
        return filter.filterSeverity(getSeverityTypeRefDescription())
                && filter.filterCustom(this)
                && filter.filterDate(getIncidentTime(), getEndTime());
    }
}
