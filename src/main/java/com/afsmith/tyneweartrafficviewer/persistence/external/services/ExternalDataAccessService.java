package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.io.IOException;
import java.util.List;

/**
 * Provides an API for accessing the external module, provides access to
 * data from the Tyne and Wear Urban Traffic Management Open Data Service.
 */
public interface ExternalDataAccessService {

    /**
     * Access data of the specified data type from the Open Data Service.
     * @param dataType The type of data to be accessed.
     * @return A list of entities of the appropriate type.
     * @param <E> The entity type that is returned.
     * @throws IOException If an issue arises when reading the data, an IOException
     * will be thrown.
     */
    <E extends TrafficEntity> List<E> getData(TrafficDataTypes dataType) throws IOException;

}
