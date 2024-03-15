package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents data on roadworks.
 */
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("roadworks")
@Entity
public class TrafficRoadwork extends TrafficPointData {
    String roadworkTypeDescription;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "planned_start_time")),
            @AttributeOverride(name = "endTime", column = @Column(name = "planned_end_time"))
    })
    PlannedTimes planned;
    PlannedTimes actual;
    String contractor;
    String trafficSignals;
    String contraflow;

    /**
     * An all-arguments constructor for roadwork data.
     */
    @Builder
    public TrafficRoadwork(String systemCodeNumber,
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
                           String roadworkTypeDescription,
                           PlannedTimes planned,
                           PlannedTimes actual,
                           String contractor,
                           String trafficSignals,
                           String contraflow,
                           List<Comment> comments,
                           User createdBy) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef, comments, createdBy);
        this.roadworkTypeDescription = roadworkTypeDescription;
        this.planned = planned;
        this.actual = actual;
        this.contractor = contractor;
        this.trafficSignals = trafficSignals;
        this.contraflow = contraflow;
    }

    /**
     * A copy constructor for roadwork data.
     * @param pointData The traffic data from which the roadwork data should be constructed.
     */
    public TrafficRoadwork(TrafficPointData pointData) {
        super(pointData);
    }

    /**
     * Get the actual start time, if that is available. Otherwise, get the planned
     * start time. If neither are available, return null.
     * @return The actual or planned start time, or null if those are unavailable.
     */
    public ZonedDateTime getStart() {
        if ((planned == null || planned.getStartTime() == null)
                && (actual == null || actual.getStartTime() == null)) return null;
        if (actual == null || actual.getStartTime() == null) return planned.getStartTime();
        return actual.getStartTime();
    }

    /**
     * Get the actual end time, if that is available. Otherwise, get the planned
     * end time. If neither are available, return null.
     * @return The actual or planned end time, or null if those are unavailable.
     */
    public ZonedDateTime getEnd() {
        if ((planned == null || planned.getEndTime() == null)
                && (actual == null || actual.getEndTime() == null)) return null;
        if (actual == null || actual.getEndTime() == null) return planned.getEndTime();
        return actual.getEndTime();
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
                && filter.filterDate(getStart(), getEnd());
    }
}
