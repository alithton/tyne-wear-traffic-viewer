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
    E externalToEntity(T externalData);
    List<E> externalToEntity(List<T> externalData);

}
