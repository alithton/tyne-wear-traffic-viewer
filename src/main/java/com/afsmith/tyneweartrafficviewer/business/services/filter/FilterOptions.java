package com.afsmith.tyneweartrafficviewer.business.services.filter;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class FilterOptions {
    List<TrafficDataTypes> dataTypes;
    Boolean includeCustomIncidents;
    List<String> severity;
    SpeedType speedType;
    Boolean currentOnly;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime startDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    ZonedDateTime endDate;

    public void setType(List<TrafficDataTypes> dataTypes) {
        this.dataTypes = dataTypes;
    }

}
