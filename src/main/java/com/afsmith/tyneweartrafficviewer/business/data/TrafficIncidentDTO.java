package com.afsmith.tyneweartrafficviewer.business.data;

public record TrafficIncidentDTO(
        String systemCodeNumber,
        String type,
        String incidentTypeDescription,
        String shortDescription,
        String locationDescription
) { }
