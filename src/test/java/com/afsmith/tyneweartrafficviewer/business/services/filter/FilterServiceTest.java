package com.afsmith.tyneweartrafficviewer.business.services.filter;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.PlannedTimes;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterServiceTest {

    @Test
    void filterSeverity() {
        List<String> severity = List.of("Low", "Medium");

        assertThat(severity.contains("Low")).isTrue();
    }

    @Test
    void includeCustom() {
        FilterOptions filterOptions = FilterOptions.builder()
                .includeCustomIncidents(true)
                .severity(List.of("Low"))
                .dataTypes(TrafficDataTypes.listTypes())
                                                   .build();

        FilterService filterService = FilterService.fromOptions(filterOptions);

        TrafficRoadwork customRoadwork = TrafficRoadwork.builder()
                .actual(new PlannedTimes(MockData.TIME, null))
                .dataSourceTypeRef("User")
                .severityTypeRefDescription("Low")
                .shortDescription(MockData.SHORT_DESCRIPTION)
                                                        .build();



        List<TrafficRoadwork> filtered = filterService.filter(List.of(customRoadwork, MockData.getRoadwork("code1")));
        assertThat(filtered.size()).isEqualTo(2);

    }

}