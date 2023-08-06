package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.TrafficEventExternalMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

/**
 * Represents data about an event that is expected to cause some disruption to traffic.
 */
@Getter
public final class TrafficEventExternal extends TrafficPointDataExternal<TrafficEvent> {

        @JsonIgnore
        private final TrafficEventExternalMapper mapper = Mappers.getMapper(TrafficEventExternalMapper.class);

        String eventTypeDescription;
        PlannedExternal planned;
        String organiser;
        String venueName;

        /**
         * Constructor for traffic event data.
         */
        @Builder
        public TrafficEventExternal(String systemCodeNumber,
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
                                    String eventTypeDescription,
                                    PlannedExternal planned,
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

        /**
         * {@inheritDoc}
         */
        @Override
        public TrafficEvent toEntity() {
                return mapper.externalToEntity(this);
        }
}

