package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.StaticJourneyTimeDeserialiser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = StaticJourneyTimeDeserialiser.class)
public class JourneytimeStaticExternal extends TrafficDataExternal<TrafficData> {
    String shortDescription;
    String longDescription;
    PointExternal point;
    PointExternal endPoint;
    ZonedDateTime lastUpdated;

    @Override
    public TrafficData toEntity() {
        return null;
    }

//    public JourneytimeStaticExternal(String systemCodeNumber,
//                                     String shortDescription,
//                                     String longDescription,
//                                     PointDTO point,
//                                     PointDTO endPoint,
//                                     ZonedDateTime lastUpdated) {
//        super(systemCodeNumber);
//        this.shortDescription = shortDescription;
//        this.longDescription = longDescription;
//        this.point = point;
//        this.endPoint = endPoint;
//        this.lastUpdated = lastUpdated;
//    }
}
