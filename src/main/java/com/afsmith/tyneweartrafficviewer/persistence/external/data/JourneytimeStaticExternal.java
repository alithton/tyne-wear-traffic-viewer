package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.JourneyTimeExternalMapper;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.StaticJourneyTimeDeserialiser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = StaticJourneyTimeDeserialiser.class)
public class JourneytimeStaticExternal extends DynamicDataExternal<JourneyTime> {

    @JsonIgnore
    JourneyTimeExternalMapper mapper = Mappers.getMapper(JourneyTimeExternalMapper.class);

    String shortDescription;
    String longDescription;
    PointExternal point;
    PointExternal endPoint;
    ZonedDateTime lastUpdated;

    @Builder
    public JourneytimeStaticExternal(String systemCodeNumber,
                                     String shortDescription,
                                     String longDescription,
                                     PointExternal point,
                                     PointExternal endPoint,
                                     ZonedDateTime lastUpdated) {
        super(systemCodeNumber);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.point = point;
        this.endPoint = endPoint;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public JourneyTime toEntity() {
        return mapper.externalToEntity(this);
    }

    @Override
    public <T extends DynamicDataExternal<JourneyTime>> JourneyTime toEntity(T other) {
        if (!(other instanceof JourneytimeDynamicExternal dynamicData)) {
            throw new IllegalArgumentException("Expect an argument of JourneytimeDynamicExternal.class, received " + other.getClass());
        }
        if (!this.systemCodeNumber.equals(dynamicData.getSystemCodeNumber())) {
            throw new RuntimeException("System code numbers for static and dynamic data must match.");
        }
        return mapper.externalToEntity(this, dynamicData);
    }
}
