package com.afsmith.tyneweartrafficviewer.persistence;


import com.afsmith.tyneweartrafficviewer.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEntity;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReaderImpl;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataPersistence;
import com.afsmith.tyneweartrafficviewer.persistence.services.TrafficDataServiceTypicalJourneyTime;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Populate the database on startup.
 */
@Component
@RequiredArgsConstructor
@Profile("!test")
public class BootstrapData implements CommandLineRunner {

    private final TrafficDataReader trafficDataReader = TrafficDataReaderImpl.fromFilePath("src/main/resources/data");
    private final TrafficDataPersistence dataPersistence;
    private final ExternalDataAccessService dataAccessService;
    private final TrafficDataServiceTypicalJourneyTime typicalJourneyTimeService;
    private final String WEEKDAY_JOURNEY_TIMES_FILE = "src/main/resources/data/Journey-Time-Data-Weekdays.csv";
    private final String WEEKEND_JOURNEY_TIMES_FILE = "src/main/resources/data/Journey-Time-Data-Weekends.csv";

    // Should the data be loaded from local files or downloaded from the Open Data Service?
    @Getter
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

        List<TrafficEntity> trafficIncidents;
        List<TrafficEntity> trafficEvents;
        List<TrafficEntity> trafficAccidents;
        List<TrafficEntity> trafficRoadworks;
        List<TrafficEntity> journeyTimes;
        List<TrafficEntity> cameras;
        List<TrafficEntity> typicalJourneyTimes = List.copyOf(typicalJourneyTimeService.loadFromFile(WEEKDAY_JOURNEY_TIMES_FILE, WEEKEND_JOURNEY_TIMES_FILE));

        // Command line flag to quickly switch between loading data from local files vs fetching from server
        if (isUseLocalData()) {
            trafficIncidents = dataAccessService.getData(TrafficDataTypes.INCIDENT, "incidents.json");
            trafficEvents = dataAccessService.getData(TrafficDataTypes.EVENT, "events.json");
            trafficAccidents = dataAccessService.getData(TrafficDataTypes.ACCIDENT, "accidents.json");
            trafficRoadworks = dataAccessService.getData(TrafficDataTypes.ROADWORKS, "roadworks.json");
            journeyTimes = dataAccessService.getData(TrafficDataTypes.SPEED,"journeytime-static.json",
                                                     "journeytime-dynamic.json");
            cameras = dataAccessService.getData(TrafficDataTypes.CAMERA, "cctv-static.json",
                                                "cctv-dynamic.json");
        } else {
            trafficIncidents = dataAccessService.getData(TrafficDataTypes.INCIDENT);
            trafficEvents = dataAccessService.getData(TrafficDataTypes.EVENT);
            trafficAccidents = dataAccessService.getData(TrafficDataTypes.ACCIDENT);
            trafficRoadworks = dataAccessService.getData(TrafficDataTypes.ROADWORKS);
            journeyTimes = dataAccessService.getData(TrafficDataTypes.SPEED);
            cameras = dataAccessService.getData(TrafficDataTypes.CAMERA);
        }
        dataPersistence.persistEntities(trafficIncidents, TrafficDataTypes.INCIDENT);
        dataPersistence.persistEntities(trafficEvents, TrafficDataTypes.EVENT);
        dataPersistence.persistEntities(trafficAccidents, TrafficDataTypes.ACCIDENT);
        dataPersistence.persistEntities(trafficRoadworks, TrafficDataTypes.ROADWORKS);
        dataPersistence.persistEntities(journeyTimes, TrafficDataTypes.SPEED);
        dataPersistence.persistEntities(cameras, TrafficDataTypes.CAMERA);
        dataPersistence.persistEntities(typicalJourneyTimes, TrafficDataTypes.TYPICAL_SPEED);
    }

    public void setUseLocalData(boolean useLocalData) {
        this.useLocalData = useLocalData;
    }
}
