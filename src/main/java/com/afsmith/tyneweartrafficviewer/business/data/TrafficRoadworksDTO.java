package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public final class TrafficRoadworksDTO extends TrafficPointDataDTO {
    String roadworkTypeDescription;
    PlannedDTO planned;
    PlannedDTO actual;
    String contractor;
    String trafficSignals;
    String contraflow;

    @Builder
    public TrafficRoadworksDTO(
            String systemCodeNumber,
            TrafficDataTypes type,
            String shortDescription,
            String longDescription,
            String locationDescription,
            PointDTO point,
            ZonedDateTime creationDate,
            String dataSourceTypeRef,
            ZonedDateTime confirmedDate,
            ZonedDateTime modifiedDate,
            String severityTypeRefDescription,
            String lanesAffectedTypeRefDescription,
            String diversionInForce,
            String phaseTypeRef,
            String roadworkTypeDescription,
            PlannedDTO planned,
            PlannedDTO actual,
            String contractor,
            String trafficSignals,
            String contraflow) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription,
              point, creationDate, dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription, diversionInForce, phaseTypeRef);
        this.roadworkTypeDescription = roadworkTypeDescription;
        this.planned = planned;
        this.actual = actual;
        this.contractor = contractor;
        this.trafficSignals = trafficSignals;
        this.contraflow = contraflow;
    }
}
