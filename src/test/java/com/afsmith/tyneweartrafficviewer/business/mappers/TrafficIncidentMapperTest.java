package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficIncidentDTO;
import com.afsmith.tyneweartrafficviewer.entities.Comment;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrafficIncidentMapperImpl.class, CommentMapperImpl.class})
class TrafficIncidentMapperTest {

    @Autowired
    TrafficIncidentMapper mapper;

    String CODE = "code1";

    TrafficIncident incident = MockData.getIncident(CODE);
    User user = MockData.getUser("username", "password");

    @Test
    void entityToDto() {

        TrafficIncidentDTO dto = mapper.entityToDto(incident);

        assertThat(dto).isNotNull();
        assertThat(dto.getSystemCodeNumber()).isEqualTo(CODE);
        assertThat(dto.getTimes().startTime()).isEqualTo(MockData.TIME);
        assertThat(dto.getTimes().endTime()).isEqualTo(MockData.END_TIME);
        assertThat(dto.getTypeDescription()).isEqualTo(MockData.TYPE_DESCRIPTION);
    }

    @Test
    void entityToDtoWithComments() {
        List<Comment> comments = List.of(MockData.getComment("content", user, incident));
        incident.setComments(comments);
        TrafficIncidentDTO dto = mapper.entityToDto(incident);

        assertThat(dto.getComments().size()).isEqualTo(1);
    }

}