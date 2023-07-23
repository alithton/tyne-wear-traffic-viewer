package com.afsmith.tyneweartrafficviewer.business.mappers;

import com.afsmith.tyneweartrafficviewer.business.data.CommentDTO;
import com.afsmith.tyneweartrafficviewer.entities.Comment;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentMapperTest {

    CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    Comment comment;

    String username = "user1";
    String content = "content";
    User user = MockData.getUser(username, "password");
    TrafficIncident incident = MockData.getIncident("code1");

    @Test
    void mapSingleComment() {

        comment = MockData.getComment(content, user, incident);
        CommentDTO dto = mapper.entityToDto(comment);

        assertThat(dto).isNotNull();
        assertThat(dto.getUserName()).isEqualTo(username);
        assertThat(dto.getContent()).isEqualTo(content);
    }

    @Test
    void mapListOfComments() {
        List<Comment> comments = List.of(MockData.getComment(content, user, incident),
                                         MockData.getComment("more content", user, incident));

        List<CommentDTO> dtos = mapper.entityToDto(comments);
        assertThat(dtos.size()).isEqualTo(2);
        assertThat(dtos.get(0).getUserName()).isEqualTo(username);
    }

}