package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents data on events that may cause traffic disruption.
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

    /**
     * An all-arguments constructor for event data.
     */
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
            List<Comment> comments,
            User createdBy) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription, lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef, comments, createdBy);
        this.eventTypeDescription = eventTypeDescription;
        this.planned = planned;
        this.organiser = organiser;
        this.venueName = venueName;
    }

    /**
     * A copy constructor for event data.
     * @param pointData The traffic data from which the event data should be constructed.
     */
    public TrafficEvent(TrafficPointData pointData) {
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
                && filter.filterDate(getPlanned().getStartTime(), getPlanned().getEndTime());
    }
}
