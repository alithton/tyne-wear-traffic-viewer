package com.afsmith.tyneweartrafficviewer.business.data;

/**
 * All categories of traffic data that are used within the application.
 */
public enum TrafficDataTypes {
    INCIDENT("/traffic/incident", TrafficIncidentDTO.class),
    ACCIDENT("", null),
    ROADWORKS("", null),
    EVENT("/traffic/event", TrafficEventDTO.class),
    SPEED("", null),
    CAMERA("", null);

    private final String url;
    private final Class<? extends TrafficDataDTO> dtoClass;

    TrafficDataTypes(String url, Class<? extends TrafficDataDTO> dtoClass) {
        this.url = url;
        this.dtoClass = dtoClass;
    }

    public String getUrl() {
        return url;
    }

    public Class<? extends TrafficDataDTO> getDtoClass() {
        return dtoClass;
    }
}
