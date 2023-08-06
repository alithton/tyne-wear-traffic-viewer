package com.afsmith.tyneweartrafficviewer.persistence.external.mappers;

import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;

import java.util.List;

/**
 * A generic mapper to provide MapStruct mappings from external data types to entities
 * corresponding to a particular traffic data type.
 * @param <T> The external data type.
 * @param <E> The data entity.
 */
public interface TrafficDataExternalMapper<T extends TrafficDataExternal<E>, E extends TrafficData> {

    /**
     * Map a single instance of the external data type to its corresponding entity.
     * @param externalData The data in its external format.
     * @return A traffic data entity.
     */
    E externalToEntity(T externalData);

    /**
     * Map a list of external data into their entities.
     * @param externalData The data in their external format.
     * @return A list of traffic data entities.
     */
    List<E> externalToEntity(List<T> externalData);

}
