package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class DynamicDataExternal<E extends TrafficEntity> extends TrafficDataExternal<E> {
    @JsonIgnore
    public DynamicDataExternal(String systemCodeNumber) {
        super(systemCodeNumber);
    }

    public abstract <T extends DynamicDataExternal<E>> E toEntity(T other);
}
