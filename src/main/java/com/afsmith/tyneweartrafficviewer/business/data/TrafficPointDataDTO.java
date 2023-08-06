package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * A data transfer object for transferring any type of traffic incident data to the frontend.
 */
@Getter
@NoArgsConstructor
public class TrafficPointDataDTO extends TrafficDataDTO {
    TrafficDataTypes type;
    String shortDescription;
    String longDescription;
    String locationDescription;
    PointDTO point;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime creationDate;
    String dataSourceTypeRef;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime confirmedDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    ZonedDateTime modifiedDate;
    String severityTypeRefDescription;
    String lanesAffectedTypeRefDescription;
    String diversionInForce;
    String phaseTypeRef;
    List<CommentDTO> comments;
    PlannedDTO times;
    String typeDescription;

    /**
     * An all-arguments constructor for traffic point data transfer objects.
     */
    public TrafficPointDataDTO(String systemCodeNumber,
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
                               List<CommentDTO> comments,
                               PlannedDTO times,
                               String typeDescription) {
        super(systemCodeNumber);
        this.type = type;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.locationDescription = locationDescription;
        this.point = point;
        this.creationDate = creationDate;
        this.dataSourceTypeRef = dataSourceTypeRef;
        this.confirmedDate = confirmedDate;
        this.modifiedDate = modifiedDate;
        this.severityTypeRefDescription = severityTypeRefDescription;
        this.lanesAffectedTypeRefDescription = lanesAffectedTypeRefDescription;
        this.diversionInForce = diversionInForce;
        this.phaseTypeRef = phaseTypeRef;
        this.comments = comments;
        this.times = times;
        this.typeDescription = typeDescription;
    }
}
