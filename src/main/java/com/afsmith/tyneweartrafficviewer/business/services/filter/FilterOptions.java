package com.afsmith.tyneweartrafficviewer.business.services.filter;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents traffic data filters as received from the frontend.
 */
@Setter
@Getter
@Builder
public class FilterOptions {

    // The types of traffic data to include.
    List<TrafficDataTypes> dataTypes;

    // Should custom incidents be included?
    Boolean includeCustomIncidents;

    // The incident severity types to include.
    List<String> severity;

    // The speed types to include.
    SpeedType speedType;

    // Should only currently active incidents be included?
    Boolean currentOnly;

    // The start of the date range to include.
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime startDate;

    // The end of the included date range.
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime endDate;

    /**
     * Set the types of traffic data to include.
     * @param dataTypes The traffic data types to include.
     */
    public void setType(List<TrafficDataTypes> dataTypes) {
        this.dataTypes = dataTypes;
    }

}
