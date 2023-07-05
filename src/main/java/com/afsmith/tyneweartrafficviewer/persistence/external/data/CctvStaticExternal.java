package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.CctvExternalMapper;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation.StaticCctvDeserialiser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

/**
 * CCTV static data provides information on the locations of traffic CCTV cameras.
 */

@Getter
@JsonDeserialize(using = StaticCctvDeserialiser.class)
public class CctvStaticExternal extends DynamicDataExternal<Camera> {

    @JsonIgnore
    CctvExternalMapper mapper = Mappers.getMapper(CctvExternalMapper.class);

    String shortDescription;
    String longDescription;
    PointExternal point;
    ZonedDateTime lastUpdated;

    @Builder
    public CctvStaticExternal(String systemCodeNumber,
                              String shortDescription,
                              String longDescription,
                              PointExternal point,
                              ZonedDateTime lastUpdated) {
        super(systemCodeNumber);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.point = point;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public <T extends DynamicDataExternal<Camera>> Camera toEntity(T other) {
        if (!(other instanceof CctvDynamicExternal dynamicData)) {
            throw new IllegalArgumentException("Expect an argument of CctvDynamicExternal.class, received " + other.getClass());
        }
        if (!this.systemCodeNumber.equals(dynamicData.getSystemCodeNumber())) {
            throw new RuntimeException("System code numbers for static and dynamic data must match.");
        }
        return mapper.externalToEntity(this, dynamicData);
    }

    @Override
    public Camera toEntity() {
        return mapper.externalToEntity(this);
    }
}
