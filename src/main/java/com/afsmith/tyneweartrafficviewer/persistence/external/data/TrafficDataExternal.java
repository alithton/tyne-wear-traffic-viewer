package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class TrafficDataExternal<E extends TrafficData> {
    String systemCodeNumber;

    /**
     * Get the entity corresponding to this external data type.
     * @return The entity version of this object.
     */
    public abstract E toEntity();
}
