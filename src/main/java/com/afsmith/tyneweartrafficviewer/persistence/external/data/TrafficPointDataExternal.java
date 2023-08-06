package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * Superclass for all external traffic incident data types.
 * @param <E> The corresponding entity.
 */
@Getter
public abstract sealed class TrafficPointDataExternal<E extends TrafficData> extends TrafficDataExternal<E>
        permits TrafficEventExternal, TrafficIncidentExternal, TrafficAccidentExternal, TrafficRoadworksExternal
         {
    TrafficDataTypes type;
    String shortDescription;
    String longDescription;
    String locationDescription;
    PointExternal point;
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

     /**
      * Constructor for traffic point data.
      */
    public TrafficPointDataExternal(String systemCodeNumber,
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
                                    String phaseTypeRef
                                    ) {
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
    }

}
