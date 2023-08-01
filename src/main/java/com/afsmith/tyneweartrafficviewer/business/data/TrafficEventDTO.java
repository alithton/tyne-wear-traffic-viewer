package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public final class TrafficEventDTO extends TrafficPointDataDTO {
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
                               String venueName,
                               List<CommentDTO> comments,
                               PlannedDTO times,
                               String typeDescription) {
                super(systemCodeNumber, type, shortDescription, longDescription, locationDescription, point,
                      creationDate,
                      dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
                      lanesAffectedTypeRefDescription,
                      diversionInForce, phaseTypeRef, comments, times, typeDescription);
                this.eventTypeDescription = eventTypeDescription;
                this.planned = planned;
                this.organiser = organiser;
                this.venueName = venueName;
        }
}

