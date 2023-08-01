package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public final class TrafficIncidentDTO extends TrafficPointDataDTO {
        String incidentTypeDescription;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime incidentTime;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        ZonedDateTime endTime;

        @Builder
        public TrafficIncidentDTO(
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
                String incidentTypeDescription,
                ZonedDateTime incidentTime,
                ZonedDateTime endTime,
                List<CommentDTO> comments,
                PlannedDTO times,
                String typeDescription) {
                super(systemCodeNumber, type, shortDescription, longDescription, locationDescription,
                      point, creationDate, dataSourceTypeRef, confirmedDate, modifiedDate, severityTypeRefDescription,
                      lanesAffectedTypeRefDescription, diversionInForce, phaseTypeRef, comments, times, typeDescription);
                this.incidentTypeDescription = incidentTypeDescription;
                this.incidentTime = incidentTime;
                this.endTime = endTime;
        }
}



