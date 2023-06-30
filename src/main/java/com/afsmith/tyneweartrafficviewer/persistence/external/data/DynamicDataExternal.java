package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class DynamicDataExternal<E extends TrafficData> extends TrafficDataExternal<E> {
    @JsonIgnore
    public DynamicDataExternal(String systemCodeNumber) {
        super(systemCodeNumber);
    }

    public abstract <T extends DynamicDataExternal<E>> E toEntity(T other);
}
