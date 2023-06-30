package com.afsmith.tyneweartrafficviewer.persistence;


import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.*;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.ExternalDataAccessService;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReader;
import com.afsmith.tyneweartrafficviewer.persistence.external.services.TrafficDataReaderImpl;
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
    private final ExternalDataAccessService dataAccessService;

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

        List<TrafficData> trafficIncidents;
        List<TrafficData> trafficEvents;
        List<TrafficData> trafficAccidents;
        List<TrafficData> trafficRoadworks;
        List<TrafficData> journeyTimes;
        List<TrafficData> cameras;

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
    }

    public boolean isUseLocalData() {
        return useLocalData;
    }

    public void setUseLocalData(boolean useLocalData) {
        this.useLocalData = useLocalData;
    }

    private <T extends TrafficDataExternal<E>, E extends TrafficData> List<TrafficData> readFromFile(String fileName, Class<T> clazz) throws IOException {
        return trafficDataReader.read(fileName, clazz)
                                .stream()
                                .map(external -> (TrafficData) external.toEntity())
                                .toList();
    }

    private <T extends TrafficDataExternal<E>, U extends TrafficDataExternal<E>, E extends TrafficData> List<TrafficData>
        readFromFile(String staticFileName, String dynamicFileName, Class<T> staticClass, Class<U> dynamicClass) throws IOException {

            List<TrafficDataExternal<E>> dynamicData = trafficDataReader.read(dynamicFileName, dynamicClass);
            return trafficDataReader.read(staticFileName, staticClass)
                                    .stream()
                                    .map(element -> {
                                        DynamicDataExternal<E> staticElement = (DynamicDataExternal<E>) element;
                                        List<TrafficDataExternal<E>> dynamic = findBySystemCode(element.getSystemCodeNumber(), dynamicData);
                                        return dynamic.size() > 0 ? staticElement.toEntity( (DynamicDataExternal<E>) dynamic.get(0))
                                                : staticElement.toEntity();
                                    })
                                    .map(element -> (TrafficData) element)
                                    .toList();
    }

    private <E extends TrafficData> List<TrafficDataExternal<E>> findBySystemCode(String code, List<TrafficDataExternal<E>> data) {
        return data.stream()
                   .filter(element -> code.equals(element.getSystemCodeNumber()))
                   .toList();
    }
}
