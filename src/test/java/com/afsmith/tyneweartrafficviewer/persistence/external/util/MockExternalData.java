package com.afsmith.tyneweartrafficviewer.persistence.external.util;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeDynamicExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.JourneytimeStaticExternal;
import com.afsmith.tyneweartrafficviewer.persistence.external.data.PointExternal;

import java.time.ZonedDateTime;

public class MockExternalData {
    private static final PointExternal point = new PointExternal(1L, 1L, 0.0, 0.0);
    private static final ZonedDateTime time = ZonedDateTime.now();

    public static JourneytimeStaticExternal getJourneyTimeStaticExternal(String code) {
        return JourneytimeStaticExternal.builder()
                                        .systemCodeNumber(code)
                                        .point(point)
                                        .endPoint(point)
                                        .shortDescription("short description")
                                        .longDescription("long description")
                                        .lastUpdated(time)
                                        .build();
    }

    public static JourneytimeDynamicExternal getJourneyTimeDynamicExternal(String code) {
        return JourneytimeDynamicExternal.builder()
                                         .systemCodeNumber(code)
                                         .linkTravelTime(100)
                                         .platesIn(0)
                                         .platesOut(10)
                                         .plateMatches(0)
                                         .lastUpdated(time)
                                         .build();
    }
}
