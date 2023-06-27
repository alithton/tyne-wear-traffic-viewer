package com.afsmith.tyneweartrafficviewer.business.data;

import com.afsmith.tyneweartrafficviewer.persistence.external.services.StaticJourneyTimeDeserialiser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = StaticJourneyTimeDeserialiser.class)
public class JourneytimeStaticDTO {
    String systemCodeNumber;
    String shortDescription;
    String longDescription;
    PointDTO point;
    PointDTO endPoint;
    ZonedDateTime lastUpdated;
}
