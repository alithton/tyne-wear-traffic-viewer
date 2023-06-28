package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.util.MockExternalData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JourneytimeDynamicExternalTest {

    @Test
    void toEntity() {
        String code = "code1";
        JourneytimeDynamicExternal dynamicData = MockExternalData.getJourneyTimeDynamicExternal(code);
        JourneyTime journeyTime = dynamicData.toEntity();

        assertThat(journeyTime).isNotNull();
        assertThat(journeyTime.getSystemCodeNumber()).isEqualTo(code);
        assertThat(journeyTime.getLastUpdatedDynamic()).isNotNull();
        assertThat(journeyTime.getLongDescription()).isNull();
        assertThat(journeyTime.getLinkTravelTime()).isEqualTo(100);
    }

    @Test
    void toEntityMatchingCodes() {
        String code = "code1";
        JourneytimeDynamicExternal dynamicData = MockExternalData.getJourneyTimeDynamicExternal(code);
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal(code);

        JourneyTime journeyTime = dynamicData.toEntity(staticData);

        assertThat(journeyTime).isNotNull();
        assertThat(journeyTime.getSystemCodeNumber()).isEqualTo(code);
        assertThat(journeyTime.getLastUpdatedDynamic()).isNotNull();
        assertThat(journeyTime.getLinkTravelTime()).isEqualTo(100);
    }

    @Test
    void toEntityMismatchedCodes() {
        JourneytimeDynamicExternal dynamicData = MockExternalData.getJourneyTimeDynamicExternal("code1");
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal("code2");

        assertThrows(RuntimeException.class, () -> dynamicData.toEntity(staticData));
    }

    /*
     * An illegal argument exception should be thrown if an argument of any type other
     * than JourneytimeDynamicExternal is passed to the toEntity method. This is
     * mainly important for catching other subtypes of JourneyTimeExternal as the
     * compiler won't spot those.
     */
    @Test
    void toEntityIllegalArgument() {
        JourneytimeDynamicExternal dynamicData1 = MockExternalData.getJourneyTimeDynamicExternal("code1");
        JourneytimeDynamicExternal dynamicData2 = MockExternalData.getJourneyTimeDynamicExternal("code1");

        assertThrows(IllegalArgumentException.class, () -> dynamicData1.toEntity(dynamicData2));
    }
}