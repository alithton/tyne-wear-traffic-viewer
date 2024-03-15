package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TrafficDataRepository;
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
 * Provides data access and persistence services for journey time data.
 * This includes loading route data for the links along which the journey time data was
 * measured. Route data can be loaded from a local JSON-formatted file or it can be
 * generated dynamically by a routing service.
 * <p>
 * Command-line flags can be used to configure the behaviour of the service.
 * The following flags are understood:
 * --local-routes - If set, routes are read from a file; otherwise the routes are generated
 *                  dynamically using the provided routing service.
 * --update-routes - If set, loaded route data is written to a file.
 */
@Service
public class TrafficDataServiceJourneyTimes {
    // The routing service, for dynamically generating route data.
    private final RoutingService routingService;

    private final TrafficDataRepository repository;
    // The file path to use for reading and writing route data.
    @Getter
    private Path routeOutputFile = Paths.get(System.getProperty("user.dir") + "/output/routes.json");

    // Should routes be read from file or generated dynamically by the routing service?
    @Setter
    private boolean routesFromFile;

    // Should the route data on file be updated once routes have finished loading?
    @Setter
    private boolean updateRoutesFile;

    /**
     * Autowired constructor that uses command line arguments to configure options.
     * @param routingService The routing service, used for dynamically generating route data.
     * @param args A Spring Boot ApplicationArguments instance. This is provided automatically
     *             when the service is initialised by Spring.
     */
    @Autowired
    public TrafficDataServiceJourneyTimes(TrafficDataRepository repository, RoutingService routingService, ApplicationArguments args) {
        this.repository = repository;
        this.routingService = routingService;
        routesFromFile = args.containsOption("local-routes");
        updateRoutesFile = args.containsOption("update-routes");
        Set<String> options = args.getOptionNames();
        System.out.println(options);
    }

    /**
     * A constructor that does not accept command line arguments. This is useful in circumstances
     * in which the Spring Boot context is not available, such as in testing.
     * @param routingService The routing service, used for dynamically generating route data.
     */
    public TrafficDataServiceJourneyTimes(TrafficDataRepository repository, RoutingService routingService) {
        this.repository = repository;
        this.routingService = routingService;
        routesFromFile = false;
        updateRoutesFile = false;
    }

    /**
     * Persist the provided journey time data to the database. The behaviour of this method is configured
     * by modifying the state of the class state (this can be set using command line flags).
     * If routesFromFile is true, attempt to load the routes from the file pointed to be the routeOutputFile
     * attribute. If no matching route is found, then an attempt is made to generate a route dynamically
     * using the routing service. If that attempt fails, the journey time data will not be persisted.
     * If updateRoutesFile is true, the route data will be written to the file path pointed to by
     * routeOutputFile, overwriting any existing file at that location.
     * @param trafficData A list of traffic data.
     */
    public void persistEntities(List<JourneyTime> trafficData) {
        List<JourneyTime> journeyTimes = downcastList(trafficData, JourneyTime.class);

        if (routesFromFile) {
            Map<String, SimpleRoute> routeMap = getRoutesFromFile();
            journeyTimes.forEach(journey -> {
                SimpleRoute route = routeMap.get(journey.getSystemCodeNumber());
                if (route == null) {
                    maybeLoadRoute(journey);
                } else {
                    journey.setRoute(route);
                }
            });
        } else {
            journeyTimes.forEach(this::loadRoute);
        }
        if (updateRoutesFile) writeRoutesToFile(journeyTimes);
        List<JourneyTime> journeyTimesWithRoutes = journeyTimes.stream()
                                                               .filter(journey -> journey.getRoute() != null)
                                                               .toList();
        repository.saveAll(journeyTimesWithRoutes);
    }

    /**
     * Change the file path used for reading and writing route data.
     * @param path The new path to use for reading and writing route data. Should
     *             be relative to the user's working directory.
     */
    public void setRouteOutputFile(String path) {
        this.routeOutputFile = Paths.get(System.getProperty("user.dir") + path);
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

    /*
     * Attempt to load a route using the routing service. If that fails with an
     * exception for any reason, set the route to null.
     */
    private void maybeLoadRoute(JourneyTime journeyTime) {
        try {
            loadRoute(journeyTime);
        } catch (Exception e) {
            journeyTime.setRoute(null);
        }
    }

    /*
     * Write route data from a provided list of journey time data to the file path
     * pointed to by routeOutputFile.
     */
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
            // Should do something more helpful here.
            e.printStackTrace();
        }
    }

    /*
     * Read route data from the file pointed to by routeOutputFile.
     * return - a map with the system code number of the related journey time data
     *          as the key and the route as the value.
     */
    private Map<String, SimpleRoute> getRoutesFromFile() {
        Map<String, SimpleRoute> routeMap = new HashMap<>();

        try {
            routeMap = routingService.read(Files.newInputStream(routeOutputFile));
        } catch (IOException e) {
            // Should do something more helpful here.
            e.printStackTrace();
        }

        return routeMap;
    }
}
