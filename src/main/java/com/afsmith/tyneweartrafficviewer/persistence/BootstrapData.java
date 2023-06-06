package com.afsmith.tyneweartrafficviewer.persistence;


import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.business.services.TrafficDataReaderImpl;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.TrafficIncidentMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.IncidentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final IncidentRepository incidentRepository;
    private final TrafficIncidentMapper incidentMapper;
    private final TrafficDataReader trafficDataReader = new TrafficDataReaderImpl();

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadIncidents();
        System.out.println("Incidents saved: " + incidentRepository.count());
    }

    /**
     * Load traffic incident data from a local file if the incident table is currently empty. This is primarily a
     * convenience for local development.
     */
    private void loadIncidents() throws IOException {
        if (incidentRepository.count() == 0) {
            Path incidentDataPath = Paths.get(System.getProperty("user.dir"), "src/main/resources/data/incidents.json");
            InputStream inputStream = Files.newInputStream(incidentDataPath);
            List<TrafficIncidentDTO> trafficIncidentDtos = trafficDataReader.read(inputStream);
            List<TrafficIncident> trafficIncidents = incidentMapper.dtoToEntity(trafficIncidentDtos);
            incidentRepository.saveAll(trafficIncidents);
        }
    }
}
