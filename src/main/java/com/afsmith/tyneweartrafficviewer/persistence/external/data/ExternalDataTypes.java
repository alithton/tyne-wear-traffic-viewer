package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;

public enum ExternalDataTypes {
    INCIDENT("/traffic/incident", TrafficIncidentExternal.class),
    ACCIDENT("/traffic/accident", TrafficAccidentExternal.class),
    ROADWORKS("/traffic/roadwork", TrafficRoadworksExternal.class),
    EVENT("/traffic/event", TrafficEventExternal.class),
    JOURNEY_TIME_STATIC("/journeytime/static", JourneytimeStaticExternal.class),
    JOURNEY_TIME_DYNAMIC("/journeytime/dynamic", null),
    CCTV_STATIC("/cctv/static", null),
    CCTV_DYNAMIC("/cctv/dynamic", null);

    private final String url;
    private final Class<?> externalClass;

    ExternalDataTypes(String url, Class<?> externalClass) {
        this.url = url;
        this.externalClass = externalClass;
    }

    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unchecked")
    public <T extends TrafficDataExternal<E>, E extends TrafficData> Class<T> getExternalClass() {
        // I know this is safe because all possible enumerations return subtypes of TrafficDataExternal
        return (Class<T>) externalClass;
    }
}
