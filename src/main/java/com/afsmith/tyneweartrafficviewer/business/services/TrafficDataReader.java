package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TrafficDataReader {
    List<TrafficDataDTO> read(InputStream src, Class<? extends TrafficDataDTO> dataClass) throws IOException;
    List<TrafficDataDTO> read(String fileName, Class<? extends TrafficDataDTO> dataClass) throws IOException;
    List<TrafficDataDTO> readFromString(String input, Class<? extends TrafficDataDTO> dataClass) throws IOException;
}
