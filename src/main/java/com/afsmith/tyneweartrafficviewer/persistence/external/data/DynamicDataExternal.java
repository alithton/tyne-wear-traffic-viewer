package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class DynamicDataExternal<E extends TrafficData> extends TrafficDataExternal<E> {
    public DynamicDataExternal(String systemCodeNumber) {
        super(systemCodeNumber);
    }

    public abstract <T extends DynamicDataExternal<E>> E toEntity(T other);
}
