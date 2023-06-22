package com.afsmith.tyneweartrafficviewer.persistence;


import com.afsmith.tyneweartrafficviewer.business.client.OpenDataServiceClient;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReaderImpl;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final TrafficDataReader trafficDataReader = TrafficDataReaderImpl.fromFilePath("src/main/resources/data");
    private final TrafficDataPersistence dataPersistence;
    private final OpenDataServiceClient client;
    private boolean useLocalData;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        if (Arrays.asList(args).contains("--local")) setUseLocalData(true);
        loadIncidents();
    }

    /**
     * Load traffic incident data from a local file if the incident table is currently empty. This is primarily a
     * convenience for local development.
     */
    public void loadIncidents() throws IOException {

        List<TrafficDataDTO> trafficIncidentDtos;
        List<TrafficDataDTO> trafficEventDTOs;

        // Command line flag to quickly switch between loading data from local files vs fetching from server
        if (isUseLocalData()) {
            trafficIncidentDtos = trafficDataReader.read("incidents.json", TrafficIncidentDTO.class);
            trafficEventDTOs = trafficDataReader.read("events.json", TrafficEventDTO.class);
        } else {
            trafficIncidentDtos = client.getData(TrafficDataTypes.INCIDENT);
            trafficEventDTOs = client.getData(TrafficDataTypes.EVENT);
        }
        dataPersistence.persist(trafficIncidentDtos, TrafficDataTypes.INCIDENT);
        dataPersistence.persist(trafficEventDTOs, TrafficDataTypes.EVENT);
    }

    public boolean isUseLocalData() {
        return useLocalData;
    }

    public void setUseLocalData(boolean useLocalData) {
        this.useLocalData = useLocalData;
    }
}
