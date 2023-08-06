package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;

import java.io.IOException;
import java.net.URL;
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
     *                     will be thrown.
     */
    <E extends TrafficEntity> List<E> getData(TrafficDataTypes dataType) throws IOException;

    /**
     * Access data of the specified type from file.
     * @param dataType The type of data to be accessed.
     * @param filePath The path to the file in which the data is stored.
     * @return A list of entities of the appropriate type.
     * @param <E> The entity type that is returned.
     * @throws IOException If an issue arises when reading the data, an IOException
     *                     will be thrown.
     */
    <E extends TrafficEntity> List<E> getData(TrafficDataTypes dataType, String filePath) throws IOException;

    /**
     * Access data of the specified type from file. This method is used for data types that
     * require the combining of static and dynamic data.
     * @param dataType The type of data to be accessed.
     * @param staticDataFilePath The path to the file in which the static data is stored.
     * @param dynamicDataFilePath The path to the file in which the dynamic data is stored.
     * @return A list of entities of the appropriate type.
     * @param <E> The entity type that is returned.
     * @throws IOException If an issue arises when reading the data, an IOException
     *                     will be thrown.
     */
    <E extends TrafficEntity> List<E> getData(TrafficDataTypes dataType, String staticDataFilePath, String dynamicDataFilePath) throws IOException;

    /**
     * Get the image specified by the provided URL.
     * @param imageUrl The URL for the requested image.
     * @return An array of image bytes.
     */
    byte[] getImage(URL imageUrl);

    /**
     * Configure the base directory to be used when reading data from files. File paths
     * are interpreted relative to this base directory path.
     * @param baseDirectory The file path to set as the base.
     */
    void setBaseDirectory(String baseDirectory);

}
