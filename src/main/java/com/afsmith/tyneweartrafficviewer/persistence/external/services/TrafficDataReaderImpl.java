package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class TrafficDataReaderImpl implements TrafficDataReader {
    private final Path workingDir;

    /**
     * Without arguments, the working directory of the traffic data reader defaults
     * to the user's current system working directory.
     */
    public TrafficDataReaderImpl() {
        workingDir = Paths.get(System.getProperty("user.dir"));
    }

    /**
     * Construct a traffic data reader with the specified path as the working
     * directory.
     * @param workingDir The absolute path of the directory to be used as the
     *                   working directory.
     */
    public TrafficDataReaderImpl(Path workingDir) {
        this.workingDir = workingDir;
    }

    /**
     * Factory method to generate new instances of traffic data reader with a provided
     * file path as the working directory. The file path can be either relative to
     * the user's current working directory or an absolute path.
     * @param filePath The path to use as the working directory for the data reader.
     * @param isRelative Whether the path should be interpreted as being relative to the user's current
     *                   working directory or absolute.
     * @return A new instance of the traffic data reader with the specified working directory set.
     */
    public static TrafficDataReaderImpl fromFilePath(String filePath, boolean isRelative) {
        if (isRelative) {
            String userWorkingDirectory = System.getProperty("user.dir");
            return new TrafficDataReaderImpl(Paths.get(userWorkingDirectory, filePath));
        } else {
            return new TrafficDataReaderImpl(Paths.get(filePath));
        }
    }

    /**
     * A shortcut factory method to generate a traffic data reader with the specified
     * working directory.
     * @param filePath The path to use as the working directory for the data reader.
     *                 This is interpreted as being relative to the user's system
     *                 working directory.
     * @return A new instance of the traffic data reader with the specified working directory set.
     */
    public static TrafficDataReaderImpl fromFilePath(String filePath) {
        return fromFilePath(filePath, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends TrafficDataExternal<E>, E extends TrafficEntity> List<T> read(InputStream src, Class<T> dataClass) throws IOException {
        var mapper = JsonMapper.builder()
                               .findAndAddModules()
                               .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                               .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                               .build()
                               .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var incidentList = mapper.getTypeFactory()
                                 .constructCollectionType(List.class, dataClass);
        return mapper.readValue(src, incidentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends TrafficDataExternal<E>, E extends TrafficEntity> List<T> read(String fileName, Class<T> dataClass) throws IOException {
        Path inputFilePath = Paths.get(workingDir.toString(), fileName);
        return read(Files.newInputStream(inputFilePath), dataClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends TrafficDataExternal<E>, E extends TrafficEntity> List<T> readFromString(String input, Class<T> dataClass) throws IOException {
        return read(new ByteArrayInputStream(input.getBytes()), dataClass);
    }
}
