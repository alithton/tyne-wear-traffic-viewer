package com.afsmith.tyneweartrafficviewer.business.data;

import lombok.*;

/**
 * A data transfer object for transferring journey time data to the frontend.
 */
@ToString
@Getter
@NoArgsConstructor
public class JourneyTimeDTO extends TrafficDataDTO implements TrafficDTO {
    String shortDescription;
    String longDescription;
    SimpleRouteDTO route;
    double speed;

    /**
     * An all-arguments constructor for journey time data transfer objects.
     */
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
