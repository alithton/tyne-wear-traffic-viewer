package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficEventDTO;
import com.afsmith.tyneweartrafficviewer.entities.TrafficEvent;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrafficEventMapperImpl.class, CommentMapperImpl.class})
class TrafficEventMapperTest {

    @Autowired
    TrafficEventMapper mapper;

    @Test
    void entityToDto() {
        String CODE = "code1";
        TrafficEvent event = MockData.getEvent(CODE);
        TrafficEventDTO dto = mapper.entityToDto(event);

        assertThat(dto).isNotNull();
        assertThat(dto.getTimes().startTime()).isEqualTo(MockData.TIME);
        assertThat(dto.getTimes().endTime()).isEqualTo(MockData.END_TIME);
        assertThat(dto.getTypeDescription()).isEqualTo(MockData.TYPE_DESCRIPTION);
    }

    @Test
    void entityListToDtoList() {
        List<TrafficEvent> events = List.of(MockData.getEvent("code1"),
                                            MockData.getEvent("code2"));
        List<TrafficEventDTO> dtos = mapper.entityToDto(events);

        assertThat(dtos.size()).isEqualTo(2);
        assertThat(dtos.get(1).getTimes().endTime()).isEqualTo(MockData.END_TIME);
    }

}