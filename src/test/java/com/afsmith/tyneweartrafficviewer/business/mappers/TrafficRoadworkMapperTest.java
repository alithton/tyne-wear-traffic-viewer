package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficRoadworksDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficRoadwork;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrafficRoadworkMapperImpl.class, CommentMapperImpl.class})
class TrafficRoadworkMapperTest {

    @Autowired
    TrafficRoadworkMapper mapper;

    @Test
    void entityToDto() {
        TrafficRoadwork roadwork = MockData.getRoadwork("code1");
        TrafficRoadworksDTO dto = mapper.entityToDto(roadwork);

        assertThat(dto).isNotNull();
        assertThat(dto.getTimes().startTime()).isEqualTo(MockData.TIME);
        assertThat(dto.getTimes().endTime()).isEqualTo(MockData.END_TIME);
        assertThat(dto.getTypeDescription()).isEqualTo(MockData.TYPE_DESCRIPTION);
    }
}