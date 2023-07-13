package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.JourneyTime;
import com.afsmith.tyneweartrafficviewer.persistence.external.util.MockExternalData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JourneytimeStaticExternalTest {

    @Test
    void toEntity() {
        String code = "code1";
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal(code);
        JourneyTime journeyTime = staticData.toEntity();

        assertThat(journeyTime).isNotNull();
        assertThat(journeyTime.getSystemCodeNumber()).isEqualTo(code);
        assertThat(journeyTime.getLastUpdated()).isNotNull();
        assertThat(journeyTime.getLinkTravelTime()).isEqualTo(0);
    }

    @Test
    void ToEntityMatchingCodes() {
        String code = "code1";
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal(code);
        JourneytimeDynamicExternal dynamicData = MockExternalData.getJourneyTimeDynamicExternal(code);

        JourneyTime journeyTime = staticData.toEntity(dynamicData);

        assertThat(journeyTime).isNotNull();
        assertThat(journeyTime.getSystemCodeNumber()).isEqualTo(code);
        assertThat(journeyTime.getLastUpdatedDynamic()).isNotNull();
        assertThat(journeyTime.getLinkTravelTime()).isEqualTo(100);
    }

    @Test
    void toEntityMismatchedCodes() {
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal("code1");
        JourneytimeDynamicExternal dynamicData = MockExternalData.getJourneyTimeDynamicExternal("code2");

        assertThrows(RuntimeException.class, () -> staticData.toEntity(dynamicData));
    }

    /*
     * An illegal argument exception should be thrown if an argument of any type other
     * than JourneytimeDynamicExternal is passed to the toEntity method. This is
     * mainly important for catching other subtypes of JourneyTimeExternal as the
     * compiler won't spot those.
     */
    @Test
    void toEntityIllegalArgument() {
        JourneytimeStaticExternal staticData = MockExternalData.getJourneyTimeStaticExternal("code1");
        JourneytimeStaticExternal staticData2 = MockExternalData.getJourneyTimeStaticExternal("code1");

        assertThrows(IllegalArgumentException.class, () -> staticData.toEntity(staticData2));
    }
}