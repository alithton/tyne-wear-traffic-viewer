package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * A data transfer object for transferring traffic roadwork data to the frontend.
 */
@Getter
public final class TrafficRoadworksDTO extends TrafficPointDataDTO {
    String roadworkTypeDescription;
    PlannedDTO planned;
    PlannedDTO actual;
    String contractor;
    String trafficSignals;
    String contraflow;

    /**
     * An all-arguments constructor for traffic roadwork data transfer objects.
     */
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
            String contraflow,
            List<CommentDTO> comments,
            PlannedDTO times,
            String typeDescription) {
        super(systemCodeNumber, type, shortDescription, longDescription, locationDescription,
              point, creationDate, dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
              lanesAffectedTypeRefDescription, diversionInForce, phaseTypeRef, comments, times, typeDescription);
        this.roadworkTypeDescription = roadworkTypeDescription;
        this.planned = planned;
        this.actual = actual;
        this.contractor = contractor;
        this.trafficSignals = trafficSignals;
        this.contraflow = contraflow;
    }
}
