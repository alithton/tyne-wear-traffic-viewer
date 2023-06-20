package com.afsmith.tyneweartrafficviewer.persistence;


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
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final TrafficDataReader trafficDataReader = TrafficDataReaderImpl.fromFilePath("src/main/resources/data");
    private final TrafficDataPersistence dataPersistence;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadIncidents();
    }

    /**
     * Load traffic incident data from a local file if the incident table is currently empty. This is primarily a
     * convenience for local development.
     */
    private void loadIncidents() throws IOException {

        List<TrafficDataDTO> trafficIncidentDtos = trafficDataReader.read("incidents.json", TrafficIncidentDTO.class);
        dataPersistence.persist(trafficIncidentDtos, TrafficDataTypes.INCIDENT);

        List<TrafficDataDTO> trafficEventDTOs = trafficDataReader.read("events.json", TrafficEventDTO.class);
        dataPersistence.persist(trafficEventDTOs, TrafficDataTypes.EVENT);
    }
}
