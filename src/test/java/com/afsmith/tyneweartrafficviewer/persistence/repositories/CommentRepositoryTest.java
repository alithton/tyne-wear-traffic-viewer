package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.Comment;
import com.afsmith.tyneweartrafficviewer.entities.TrafficIncident;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TrafficDataRepository dataRepository;

    @Autowired
    UserRepository userRepository;

    TrafficIncident pointData = MockData.getIncident("code1");
    User user1 = MockData.getUser("user1", "password");
    User user2 = MockData.getUser("user2", "p2");
    List<Comment> comments = List.of(MockData.getComment("comment", user1, pointData),
                                     MockData.getComment("comment", user2, pointData));

    @BeforeEach
    void setUp() {
        userRepository.saveAll(List.of(user1, user2));
        dataRepository.saveAndFlush(pointData);
        commentRepository.saveAllAndFlush(comments);
    }

    @Test
    void commentsAreSaved() {
        assertThat(commentRepository.count()).isEqualTo(2);
    }

    @Test
    void commentsCanBeFound() {
        List<Comment> found = commentRepository.findAll();

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getTrafficData()).isEqualTo(pointData);
    }
}