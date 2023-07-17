package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CameraDTO;
import com.afsmith.tyneweartrafficviewer.entities.Camera;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class CameraMapperTest {
    CameraMapper mapper = Mappers.getMapper(CameraMapper.class);
    String CODE = "code1";

    @Test
    public void entityToDto() {
        Camera entity = MockData.getCamera(CODE);
        CameraDTO dto = mapper.entityToDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(dto.getImage()).isEqualTo(MockData.IMAGE_URL);
    }
}