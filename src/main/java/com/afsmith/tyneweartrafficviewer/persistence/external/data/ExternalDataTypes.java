package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import lombok.Getter;

/**
 * An enumeration of available external data types that are handled by the module.
 * Each data type is associated with the API route that should be provided to the
 * Open Data Service to request data of that type.
 */
public enum ExternalDataTypes {
    INCIDENT("/traffic/incident", TrafficIncidentExternal.class),
    ACCIDENT("/traffic/accident", TrafficAccidentExternal.class),
    ROADWORKS("/traffic/roadwork", TrafficRoadworksExternal.class),
    EVENT("/traffic/event", TrafficEventExternal.class),
    JOURNEY_TIME_STATIC("/journeytime/static", JourneytimeStaticExternal.class),
    JOURNEY_TIME_DYNAMIC("/journeytime/dynamic", JourneytimeDynamicExternal.class),
    CCTV_STATIC("/cctv/static", CctvStaticExternal.class),
    CCTV_DYNAMIC("/cctv/dynamic", CctvDynamicExternal.class);

    // The open data service API route for this data type.
    @Getter
    private final String url;

    // The class representing the data type.
    private final Class<?> externalClass;

    ExternalDataTypes(String url, Class<?> externalClass) {
        this.url = url;
        this.externalClass = externalClass;
    }

    /**
     * Get the class that represents the data type within the External module.
     * @return The class representing the data type.
     * @param <T> The external data type.
     * @param <E> The corresponding entity.
     */
    @SuppressWarnings("unchecked")
    public <T extends TrafficDataExternal<E>, E extends TrafficEntity> Class<T> getExternalClass() {
        // I know this is safe because all possible enumerations return subtypes of TrafficDataExternal
        return (Class<T>) externalClass;
    }
}
