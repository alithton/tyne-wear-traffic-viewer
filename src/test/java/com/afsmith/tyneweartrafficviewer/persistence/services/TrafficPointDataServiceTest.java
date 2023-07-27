package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.entities.TrafficPointData;
import com.afsmith.tyneweartrafficviewer.persistence.repositories.PointDataRepository;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = TrafficPointDataService.class))
class TrafficPointDataServiceTest {

    @Autowired
    TrafficPointDataService pointDataService;

    @Autowired
    PointDataRepository<TrafficPointData> repository;

    @BeforeEach
    void setUp() {
        List<TrafficIncident> incidents = List.of(MockData.getIncident("code1"),
                                                  MockData.getIncident("code2"));
        repository.saveAll(incidents);
    }

    @Test
    void findByCodeNumber() {
        String code = "code1";
        TrafficPointData foundData = pointDataService.findByCodeNumber(code);

        assertThat(foundData).isNotNull();
        assertThat(foundData).isInstanceOf(TrafficIncident.class);

        TrafficIncident incident = (TrafficIncident) foundData;

        assertThat(incident.getIncidentTime()).isNotNull();
    }

}