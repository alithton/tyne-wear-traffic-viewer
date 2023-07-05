package com.afsmith.tyneweartrafficviewer.persistence.external.data;

import com.afsmith.tyneweartrafficviewer.persistence.entities.Camera;
import com.afsmith.tyneweartrafficviewer.persistence.external.util.MockExternalData;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

class CctvStaticExternalTest {

    String code1 = "code1";

    @Test
    void cctvStaticExternalToEntity() {
        CctvStaticExternal cctvStatic = MockExternalData.getCctvStaticExternal(code1);
        Camera camera = cctvStatic.toEntity();

        assertThat(camera).isNotNull();
        assertThat(camera.getSystemCodeNumber()).isEqualTo(code1);
        assertThat(camera.getImage()).isNull();
    }

    @Test
    void cctvStaticAndDynamicExternalToEntity() throws MalformedURLException {
        var cctvStatic = MockExternalData.getCctvStaticExternal(code1);
        var cctvDynamic = MockExternalData.getCctvDynamicExternal(code1);

        Camera camera = cctvStatic.toEntity(cctvDynamic);

        assertThat(camera.getSystemCodeNumber()).isEqualTo(code1);
        assertThat(camera.getImage()).isEqualTo(MockExternalData.getImageUrl());
        assertThat(camera.getLastUpdated()).isEqualTo(MockExternalData.TIME);
        assertThat(camera.getLastUpdatedDynamic()).isEqualTo(MockExternalData.TIME);
    }
}