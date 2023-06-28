package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class TrafficPointData extends TrafficData {
    private TrafficDataTypes type;
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    private String locationDescription;
    private Point point;
    private ZonedDateTime creationDate;
    private String dataSourceTypeRef;
    private ZonedDateTime confirmedDate;
    private ZonedDateTime modifiedDate;
    private String severityTypeRefDescription;
    private String lanesAffectedTypeRefDescription;
    private String diversionInForce;
    private String phaseTypeRef;

    public TrafficPointData(String systemCodeNumber,
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
                            String phaseTypeRef) {
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
