package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Getter
@NoArgsConstructor
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
                           String contraflow) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point, creationDate,
              dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription,
              diversionInForce, phaseTypeRef);
        this.roadworkTypeDescription = roadworkTypeDescription;
        this.planned = planned;
        this.actual = actual;
        this.contractor = contractor;
        this.trafficSignals = trafficSignals;
        this.contraflow = contraflow;
    }
}
