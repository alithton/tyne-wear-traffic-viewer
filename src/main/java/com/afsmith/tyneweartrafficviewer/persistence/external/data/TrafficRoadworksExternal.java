package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public final class TrafficRoadworksExternal extends TrafficPointDataExternal<TrafficData> {
    String roadworkTypeDescription;
    PlannedExternal planned;
    PlannedExternal actual;
    String contractor;
    String trafficSignals;
    String contraflow;

    @Builder
    public TrafficRoadworksExternal(
            String systemCodeNumber,
            TrafficDataTypes type,
            String shortDescription,
            String longDescription,
            String locationDescription,
            PointExternal point,
            ZonedDateTime creationDate,
            String dataSourceTypeRef,
            ZonedDateTime confirmedDate,
            ZonedDateTime modifiedDate,
            String severityTypeRefDescription,
            String lanesAffectedTypeRefDescription,
            String diversionInForce,
            String phaseTypeRef,
            String roadworkTypeDescription,
            PlannedExternal planned,
            PlannedExternal actual,
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


    @Override
    public TrafficData toEntity() {
        return null;
    }
}
