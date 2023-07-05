package com.afsmith.tyneweartrafficviewer.persistence.external.util;

import com.afsmith.tyneweartrafficviewer.persistence.external.data.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;

public class MockExternalData {
    public static final PointExternal POINT = new PointExternal(1L, 1L, 0.0, 0.0);
    public static final ZonedDateTime TIME = ZonedDateTime.now();

    public static JourneytimeStaticExternal getJourneyTimeStaticExternal(String code) {
        return JourneytimeStaticExternal.builder()
                                        .systemCodeNumber(code)
                                        .point(POINT)
                                        .endPoint(POINT)
                                        .shortDescription("short description")
                                        .longDescription("long description")
                                        .lastUpdated(TIME)
                                        .build();
    }

    public static JourneytimeDynamicExternal getJourneyTimeDynamicExternal(String code) {
        return JourneytimeDynamicExternal.builder()
                                         .systemCodeNumber(code)
                                         .linkTravelTime(100)
                                         .platesIn(0)
                                         .platesOut(10)
                                         .plateMatches(0)
                                         .lastUpdated(TIME)
                                         .build();
    }

    public static CctvStaticExternal getCctvStaticExternal(String code) {
        return CctvStaticExternal.builder()
                                 .systemCodeNumber(code)
                                 .shortDescription("short description")
                                 .longDescription("long description")
                                 .point(POINT)
                                 .lastUpdated(TIME)
                                 .build();
    }

    public static CctvDynamicExternal getCctvDynamicExternal(String code) throws MalformedURLException {
        return CctvDynamicExternal.builder()
                                  .systemCodeNumber(code)
                                  .image(getImageUrl())
                                  .lastUpdated(TIME)
                                  .build();
    }

    public static URL getImageUrl() throws MalformedURLException {
        return new URL("https://example.com/image.jpg");
    }
}
