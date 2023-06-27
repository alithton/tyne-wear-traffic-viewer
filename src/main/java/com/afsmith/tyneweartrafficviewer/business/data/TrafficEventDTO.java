package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public final class TrafficEventDTO extends TrafficDataDTO {
        String eventTypeDescription;
        PlannedDTO planned;
        String organiser;
        String venueName;

        @Builder
        public TrafficEventDTO(String systemCodeNumber,
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
                               String eventTypeDescription,
                               PlannedDTO planned,
                               String organiser,
                               String venueName) {
                super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point,
                      creationDate,
                      dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
                      lanesAffectedTypeRefDescription,
                      diversionInForce, phaseTypeRef);
                this.eventTypeDescription = eventTypeDescription;
                this.planned = planned;
                this.organiser = organiser;
                this.venueName = venueName;
        }
}

