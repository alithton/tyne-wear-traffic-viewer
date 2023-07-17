package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.external.mappers.CctvExternalMapper;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.deserialisation.DynamicCctvDeserialiser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * CCTV dynamic data provides links to images from traffic CCTV cameras.
 */
@Getter
@JsonDeserialize(using = DynamicCctvDeserialiser.class)
public class CctvDynamicExternal extends DynamicDataExternal<Camera>{

    @JsonIgnore
    CctvExternalMapper mapper = Mappers.getMapper(CctvExternalMapper.class);

    URL image;
    ZonedDateTime lastUpdated;

    @Builder
    public CctvDynamicExternal(String systemCodeNumber, URL image, ZonedDateTime lastUpdated) {
        super(systemCodeNumber);
        this.image = image;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public <T extends DynamicDataExternal<Camera>> Camera toEntity(T other) {
        if (! (other instanceof CctvStaticExternal staticData)) {
            throw new IllegalArgumentException("Expect an argument of CctvStaticExternal.class, received " + other.getClass());
        }
        if (!this.systemCodeNumber.equals(staticData.getSystemCodeNumber())) {
            throw new RuntimeException("System code numbers for static and dynamic data must match.");
        }
        return mapper.externalToEntity(staticData, this);
    }

    @Override
    public Camera toEntity() {
        return mapper.externalToEntity(this);
    }
}
