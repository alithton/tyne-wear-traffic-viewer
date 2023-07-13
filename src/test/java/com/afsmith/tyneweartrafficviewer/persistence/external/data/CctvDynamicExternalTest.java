package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.entities.Point;
import com.afsmith.tyneweartrafficviewer.persistence.external.util.MockExternalData;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

class CctvDynamicExternalTest {

    String CODE = "code1";

    @Test
    void toEntity() throws MalformedURLException {
        var cctvDynamic = MockExternalData.getCctvDynamicExternal(CODE);

        Camera camera = cctvDynamic.toEntity();
        assertThat(camera.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(camera.getImage()).isEqualTo(MockExternalData.getImageUrl());
        assertThat(camera.getLastUpdated()).isEqualTo(MockExternalData.TIME);
        assertThat(camera.getLongDescription()).isNull();
    }

    @Test
    void testToEntity() throws MalformedURLException {
        var cctvStatic = MockExternalData.getCctvStaticExternal(CODE);
        var cctvDynamic = MockExternalData.getCctvDynamicExternal(CODE);

        Camera camera = cctvDynamic.toEntity(cctvStatic);
        assertThat(camera.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(camera.getPoint()).isInstanceOf(Point.class);
        assertThat(camera.getLongDescription()).isNotNull();
    }
}