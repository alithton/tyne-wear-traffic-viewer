package com.afsmith.tyneweartrafficviewer.business.services;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class TrafficDataReaderImpl implements TrafficDataReader {
    @Override
    public List<TrafficIncidentDTO> read(InputStream src) throws IOException {
        var mapper = JsonMapper.builder()
                .findAndAddModules()
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .build()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var incidentList = mapper.getTypeFactory()
                .constructCollectionType(List.class, TrafficIncidentDTO.class);
        return mapper.readValue(src, incidentList);
    }
}
