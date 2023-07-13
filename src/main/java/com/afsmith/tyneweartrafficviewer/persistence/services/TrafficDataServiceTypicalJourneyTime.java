package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TypicalJourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.TypicalJourneyTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataServiceTypicalJourneyTime extends AbstractTrafficDataService<TypicalJourneyTime> {
    public TrafficDataServiceTypicalJourneyTime(TypicalJourneyTimeRepository repository) {
        super(repository, TypicalJourneyTime.class);
    }
}
