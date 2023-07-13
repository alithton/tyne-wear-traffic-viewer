package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.JourneyTimeRepository;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

/**
 * Provides data access and persistence services for static and dynamic journey time data.
 * This includes loading route data for the links along which the journey time data was
 * measured. Route data can be loaded from a local JSON-formatted file or it can be
 * generated dynamically by a routing service.
 */
@Service
public class TrafficDataServiceJourneyTimes extends AbstractTrafficDataService<JourneyTime, JourneyTimeDTO, String> {
    private final RoutingService routingService;

    @Getter
    private Path routeOutputFile = Paths.get(System.getProperty("user.dir") + "/output/routes.json");

    @Setter
    private boolean routesFromFile;

    @Autowired
    public TrafficDataServiceJourneyTimes(JourneyTimeMapper mapper, JourneyTimeRepository repository, RoutingService routingService, ApplicationArguments args) {
        super(mapper, repository, JourneyTimeDTO.class, JourneyTime.class);
        this.routingService = routingService;
        routesFromFile = args.containsOption("local-routes");
        Set<String> options = args.getOptionNames();
        System.out.println(options);
    }

    public TrafficDataServiceJourneyTimes(JourneyTimeMapper mapper, JourneyTimeRepository repository, RoutingService routingService) {
        super(mapper, repository, JourneyTimeDTO.class, JourneyTime.class);
        this.routingService = routingService;
        routesFromFile = false;
    }

    @Override
    public void persistEntities(List<TrafficData> trafficData) {
        List<JourneyTime> journeyTimes = downcastList(trafficData, JourneyTime.class);

        if (routesFromFile) {
            Map<String, SimpleRoute> routeMap = getRoutesFromFile();
            journeyTimes.forEach(journey -> {
                SimpleRoute route = routeMap.get(journey.getSystemCodeNumber());
                journey.setRoute(route);
            });
        } else {
            journeyTimes.forEach(this::loadRoute);
        }
        writeRoutesToFile(journeyTimes);
        repository.saveAll(journeyTimes);
    }

    /*
     * Get the route data for the provided journey, if it is not already present.
     */
    private void loadRoute(JourneyTime journeyTime) {

        // Avoid re-calculating existing routes.
        if (journeyTime.getRoute() != null) return;

        SimpleRoute route = routingService.calculateRoute(journeyTime.getPoint(), journeyTime.getEndPoint());
        journeyTime.setRoute(route);
    }

    private void writeRoutesToFile(List<JourneyTime> journeyTimes) {
        Map<String, SimpleRoute> routeMap = new HashMap<>();

        journeyTimes.forEach(journey -> {
            routeMap.put(journey.getSystemCodeNumber(), journey.getRoute());
        });

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(Files.newOutputStream(routeOutputFile), routeMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, SimpleRoute> getRoutesFromFile() {
        Map<String, SimpleRoute> routeMap = new HashMap<>();

        try {
            routeMap = routingService.read(Files.newInputStream(routeOutputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routeMap;
    }

    public void setRouteOutputFile(String path) {
        this.routeOutputFile = Paths.get(System.getProperty("user.dir") + path);
    }

    public boolean isRoutesFromFile() {
        return routesFromFile;
    }
}
