package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

/**
 * Abstract class representing traffic data types that are split into static and
 * dynamic components in the Open Data Service.
 * @param <E> The traffic data entity to which the external data is mapped.
 */
@NoArgsConstructor
public abstract class DynamicDataExternal<E extends TrafficEntity> extends TrafficDataExternal<E> {

    /**
     * Super class constructor for use by concrete classes extending this one.
     */
    @JsonIgnore
    public DynamicDataExternal(String systemCodeNumber) {
        super(systemCodeNumber);
    }

    /**
     * Combine data with a matching static or dynamic counterpart to produce the
     * corresponding entity data, which does not distinguish between static and
     * dynamic components.
     * @param other The matching static or dynamic data. Implementations should include
     *              checks that ensure this provided data is of the correct type and
     *              that the system code numbers match.
     * @return The entity data.
     * @param <T> The type of the static or dynamic external data.
     */
    public abstract <T extends DynamicDataExternal<E>> E toEntity(T other);
}
