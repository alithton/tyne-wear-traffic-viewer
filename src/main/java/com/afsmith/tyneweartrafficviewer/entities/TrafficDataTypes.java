package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.*;
import lombok.Getter;

import java.util.List;

/**
 * All categories of traffic data that are used within the application. Each data
 * type is associated with an entity class, which is the representation used internally
 * within the backend and for database storage. Each data type is also associated
 * with a data transfer object (DTO) class, which is the representation used to
 * transfer data of that type to the frontend.
 */
@Getter
public enum TrafficDataTypes {
    INCIDENT(TrafficIncidentDTO.class, TrafficIncident.class),
    ACCIDENT(TrafficAccidentDTO.class, TrafficAccident.class),
    ROADWORKS(TrafficRoadworksDTO.class, TrafficRoadwork.class),
    EVENT(TrafficEventDTO.class, TrafficEvent.class),
    SPEED(JourneyTimeDTO.class, JourneyTime.class),
    TYPICAL_SPEED(JourneyTimeDTO.class, TypicalJourneyTime.class),
    CAMERA(CameraDTO.class, Camera.class);

    private final Class<? extends TrafficDataDTO> dtoClass;
    private final Class<? extends TrafficEntity> entityClass;

    /**
     * Constructor for the data type.
     * @param dtoClass The DTO class associated with the data type.
     * @param entityClass The entity class associated with the data type.
     */
    TrafficDataTypes(Class<? extends TrafficDataDTO> dtoClass,
                     Class<? extends TrafficEntity> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    /**
     * Get a list of all available traffic data types.
     * @return A list of traffic data types.
     */
    public static List<TrafficDataTypes> listTypes() {
        return List.of(TrafficDataTypes.values());
    }
}
