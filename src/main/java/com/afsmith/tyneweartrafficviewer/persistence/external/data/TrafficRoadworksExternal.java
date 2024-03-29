package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.TrafficRoadworkExternalMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

/**
 * Represents data about roadworks.
 */
@Getter
public final class TrafficRoadworksExternal extends TrafficPointDataExternal<TrafficRoadwork> {
    @JsonIgnore
    TrafficRoadworkExternalMapper mapper = Mappers.getMapper(TrafficRoadworkExternalMapper.class);
    String roadworkTypeDescription;
    PlannedExternal planned;
    PlannedExternal actual;
    String contractor;
    String trafficSignals;
    String contraflow;

    /**
     * Constructor for roadwork data.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public TrafficRoadwork toEntity() {
        return mapper.externalToEntity(this);
    }
}
