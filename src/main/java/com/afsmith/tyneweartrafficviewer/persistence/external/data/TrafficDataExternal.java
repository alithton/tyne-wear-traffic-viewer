package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Abstract super class to all external traffic data types. Each external traffic data
 * class is related to an entity, which is the internal representation of that data type
 * and must implement the TrafficEntity interface.
 * @param <E> The corresponding entity type.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class TrafficDataExternal<E extends TrafficEntity> {

    String systemCodeNumber;

    /**
     * Get the entity corresponding to this external data type.
     * @return The entity version of this object.
     */
    public abstract E toEntity();
}
