package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.business.data.JourneyTimeDTO;
import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.entities.SimpleRoute;
import com.afsmith.tyneweartrafficviewer.persistence.entities.TrafficData;
import com.afsmith.tyneweartrafficviewer.persistence.mappers.JourneyTimeMapper;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.JourneyTimeRepository;
import com.afsmith.tyneweartrafficviewer.persistence.routing.services.RoutingService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.afsmith.tyneweartrafficviewer.util.TypeConversionLibrary.downcastList;

@Service
public class TrafficDataServiceJourneyTimes extends AbstractTrafficDataService<JourneyTime, JourneyTimeDTO, String> {
    private final RoutingService routingService;

    public TrafficDataServiceJourneyTimes(JourneyTimeMapper mapper, JourneyTimeRepository repository, RoutingService routingService) {
        super(mapper, repository, JourneyTimeDTO.class, JourneyTime.class);
        this.routingService = routingService;
    }

    @Override
    public void persistEntities(List<TrafficData> trafficData) {
        List<JourneyTime> journeyTimes = downcastList(trafficData, JourneyTime.class);
        journeyTimes.forEach(this::loadRoute);
        repository.saveAll(journeyTimes);
    }

    private void loadRoute(JourneyTime journeyTime) {

        // Avoid re-calculating existing routes.
        if (journeyTime.getRoute() != null) return;

        SimpleRoute route = routingService.calculateRoute(journeyTime.getPoint(), journeyTime.getEndPoint());
        journeyTime.setRoute(route);
    }
}
