package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TypicalJourneyTimeDTO implements MappableDTO {
    private String systemCodeNumber;
    String shortDescription;
    String longDescription;
    double averageSpeed;
    SimpleRouteDTO route;
}
