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
    private final ExternalDataAccessService dataAccessService;
    private final TrafficDataServiceTypicalJourneyTime typicalJourneyTimeService;
    private final String WEEKDAY_JOURNEY_TIMES_FILE = "src/main/resources/data/Journey-Time-Data-Weekdays.csv";
    private final String WEEKEND_JOURNEY_TIMES_FILE = "src/main/resources/data/Journey-Time-Data-Weekends.csv";

    // Should the data be loaded from local files or downloaded from the Open Data Service?
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
            trafficIncidents = readFromFile("incidents.json", TrafficIncidentExternal.class);
            trafficEvents = readFromFile("events.json", TrafficEventExternal.class);
            trafficAccidents = readFromFile("accidents.json", TrafficAccidentExternal.class);
            trafficRoadworks = readFromFile("roadworks.json", TrafficRoadworksExternal.class);
            journeyTimes = readFromFile("journeytime-static.json", "journeytime-dynamic.json",
                                        JourneytimeStaticExternal.class, JourneytimeDynamicExternal.class);
            cameras = readFromFile("cctv-static.json", "cctv-dynamic.json",
                                   CctvStaticExternal.class, CctvDynamicExternal.class);

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

    public boolean isUseLocalData() {
        return useLocalData;
    }

    public void setUseLocalData(boolean useLocalData) {
        this.useLocalData = useLocalData;
    }

    private <T extends TrafficDataExternal<E>, E extends TrafficData> List<TrafficEntity> readFromFile(String fileName, Class<T> clazz) throws IOException {
        return trafficDataReader.read(fileName, clazz)
                                .stream()
                                .map(external -> (TrafficEntity) external.toEntity())
                                .toList();
    }

    @SuppressWarnings("unchecked")
    private <T extends TrafficDataExternal<E>, U extends TrafficDataExternal<E>, E extends TrafficEntity> List<TrafficEntity>
        readFromFile(String staticFileName, String dynamicFileName, Class<? extends T> staticClass, Class<U> dynamicClass) throws IOException {

            List<U> dynamicData = trafficDataReader.read(dynamicFileName, dynamicClass);
            return trafficDataReader.read(staticFileName, staticClass)
                                    .stream()
                                    .map(element -> {
                                        DynamicDataExternal<E> staticElement = (DynamicDataExternal<E>) element;
                                        List<U> dynamic = findBySystemCode(element.getSystemCodeNumber(), dynamicData);
                                        return dynamic.size() > 0 ? staticElement.toEntity( (DynamicDataExternal<E>) dynamic.get(0))
                                                : staticElement.toEntity();
                                    })
                                    .map(element -> (TrafficEntity) element)
                                    .toList();
    }

    private <T extends TrafficDataExternal<E>, E extends TrafficEntity> List<T> findBySystemCode(String code, List<T> data) {
        return data.stream()
                   .filter(element -> code.equals(element.getSystemCodeNumber()))
                   .toList();
    }
}
