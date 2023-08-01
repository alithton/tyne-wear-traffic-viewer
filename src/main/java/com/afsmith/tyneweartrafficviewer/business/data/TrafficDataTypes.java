package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.entities.*;

import java.util.List;

/**
 * All categories of traffic data that are used within the application.
 */
public enum TrafficDataTypes {
    INCIDENT("/traffic/incident", TrafficIncidentDTO.class, TrafficIncident.class),
    ACCIDENT("", TrafficAccidentDTO.class, TrafficAccident.class),
    ROADWORKS("", TrafficRoadworksDTO.class, TrafficRoadwork.class),
    EVENT("/traffic/event", TrafficEventDTO.class, TrafficEvent.class),
    SPEED("", JourneyTimeDTO.class, JourneyTime.class),
    TYPICAL_SPEED("", JourneyTimeDTO.class, TypicalJourneyTime.class),
    CAMERA("", CameraDTO.class, Camera.class);

    private final String url;
    private final Class<? extends TrafficDataDTO> dtoClass;
    private final Class<? extends TrafficEntity> entityClass;

    TrafficDataTypes(String url, Class<? extends TrafficDataDTO> dtoClass, Class<? extends TrafficEntity> entityClass) {
        this.url = url;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public String getUrl() {
        return url;
    }

    public Class<? extends TrafficDataDTO> getDtoClass() {
        return dtoClass;
    }

    public Class<? extends TrafficEntity> getEntityClass() {
        return entityClass;
    }

    public static List<TrafficDataTypes> listTypes() {
        return List.of(TrafficDataTypes.values());
    }
}
