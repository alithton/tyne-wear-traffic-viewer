package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a traffic accident.
 */
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("accident")
@Entity
public class TrafficAccident extends TrafficPointData {
    String accidentTypeDescription;
    ZonedDateTime accidentTime;
    ZonedDateTime endTime;

    /**
     * An all-arguments constructor for traffic accident data.
     */
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

    /**
     * A copy constructor for traffic accident from a generic traffic point data instance.
     * @param pointData The traffic incident data from which to construct the traffic accident data.
     */
    public TrafficAccident(TrafficPointData pointData) {
        super(pointData);
    }

    /**
     *
     * @param filter
     * @return
     */
    @Override
    public boolean isIncluded(FilterService filter) {
        return filter.filterSeverity(getSeverityTypeRefDescription())
                && filter.filterCustom(this)
                && filter.filterDate(getAccidentTime(), getEndTime());
    }
}
