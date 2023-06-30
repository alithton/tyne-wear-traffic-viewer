package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Provides methods for reading traffic data in various input formats into the program.
 */
public interface TrafficDataReader {

    /**
     * Read the provided data into the specified subclass of TrafficDataExternal.
     * Throws an IOException if this is not possible.
     * @param src An InputStream containing the traffic data.
     * @param dataClass The class that should be returned.
     * @return A list of the specified class.
     * @param <E> The corresponding entity.
     */
    <E extends TrafficData> List<TrafficDataExternal<E>> read(InputStream src, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;

    /**
     * Read the provided data into the specified subclass of TrafficDataExternal.
     * Throws an IOException if this is not possible.
     * @param fileName The file name or path from which data should be read.
     * @param dataClass The class that should be returned.
     * @return A list of the specified class.
     * @param <E> The corresponding entity.
     */
    <E extends TrafficData> List<TrafficDataExternal<E>> read(String fileName, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;

    /**
     * Read the provided data into the specified subclass of TrafficDataExternal.
     * Throws an IOException if this is not possible.
     * @param input A string from which data should be read.
     * @param dataClass The class that should be returned.
     * @return A list of the specified class.
     * @param <E> The corresponding entity.
     */
    <E extends TrafficData> List<TrafficDataExternal<E>> readFromString(String input, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;
}
