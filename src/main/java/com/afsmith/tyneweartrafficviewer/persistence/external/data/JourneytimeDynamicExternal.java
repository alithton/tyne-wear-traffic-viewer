package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.JourneyTimeExternalMapper;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.DynamicJourneyTimeDeserialiser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Getter
@JsonDeserialize(using = DynamicJourneyTimeDeserialiser.class)
public class JourneytimeDynamicExternal extends DynamicDataExternal<JourneyTime> {

    @JsonIgnore
    JourneyTimeExternalMapper mapper = Mappers.getMapper(JourneyTimeExternalMapper.class);

    int linkTravelTime;
    int platesIn;
    int platesOut;
    int plateMatches;
    ZonedDateTime lastUpdated;

    @Builder
    public JourneytimeDynamicExternal(String systemCodeNumber,
                                      int linkTravelTime,
                                      int platesIn,
                                      int platesOut,
                                      int plateMatches,
                                      ZonedDateTime lastUpdated) {
        super(systemCodeNumber);
        this.linkTravelTime = linkTravelTime;
        this.platesIn = platesIn;
        this.platesOut = platesOut;
        this.plateMatches = plateMatches;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public JourneyTime toEntity() {
        return mapper.externalToEntity(this);
    }

    @Override
    public <T extends DynamicDataExternal<JourneyTime>> JourneyTime toEntity(T other) {
        if (! (other instanceof JourneytimeStaticExternal staticData)) {
            throw new IllegalArgumentException("Expect an argument of JourneytimeStaticExternal.class, received " + other.getClass());
        }
        if (!this.systemCodeNumber.equals(staticData.getSystemCodeNumber())) {
            throw new RuntimeException("System code numbers for static and dynamic data must match.");
        }
        return mapper.externalToEntity(staticData, this);
    }
}
