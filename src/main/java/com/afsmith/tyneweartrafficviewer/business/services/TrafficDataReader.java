package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TrafficDataReader {
    List<TrafficIncidentDTO> read(InputStream src) throws IOException;
}
