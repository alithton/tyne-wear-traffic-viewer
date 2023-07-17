package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.business.mappers.MappableDTO;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class JourneyTimeDTO extends TrafficDataDTO implements MappableDTO {
    String shortDescription;
    String longDescription;
    SimpleRouteDTO route;
    double speed;

    @Builder
    public JourneyTimeDTO(String systemCodeNumber,
                          String shortDescription,
                          String longDescription,
                          SimpleRouteDTO route,
                          double speed) {
        super(systemCodeNumber);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.route = route;
        this.speed = speed;
    }
}
