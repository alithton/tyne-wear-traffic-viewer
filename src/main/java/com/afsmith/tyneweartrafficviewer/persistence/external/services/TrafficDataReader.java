package com.afsmith.tyneweartrafficviewer.persistence.external.services;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.TrafficDataExternal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TrafficDataReader {
    <E extends TrafficData> List<TrafficDataExternal<E>> read(InputStream src, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;
    <E extends TrafficData> List<TrafficDataExternal<E>> read(String fileName, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;
    <E extends TrafficData> List<TrafficDataExternal<E>> readFromString(String input, Class<? extends TrafficDataExternal<E>> dataClass) throws IOException;
}
