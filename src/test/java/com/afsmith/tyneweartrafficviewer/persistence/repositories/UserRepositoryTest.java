package com.afsmith.tyneweartrafficviewer.persistence.repositories;

import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    List<User> users;

    String P1 = "password1";
    String P2 = "password2";
    String U1 = "username1";
    String U2 = "username2";

    @BeforeEach
    void setUp() {
        users = List.of(MockData.getUser(U1, P1),
                        MockData.getUser(U2, P2));
        repository.saveAll(users);
    }

    @Test
    void usersAddedToRepository() {
        assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    void findUserByUsernameAndPassword() {
        User user = repository.findUserByCredentialsUsername(U1);
        assertThat(user).isNotNull();
        assertThat(user.getCredentials().getPassword()).isEqualTo(P1);
        assertThat(user.getCredentials().getUsername()).isEqualTo(U1);
    }

    @Test
    void failToFindUserWhenCredentialsNotPresent() {
        User user = repository.findUserByCredentialsUsername("not_present");
        assertThat(user).isNull();
    }

    @Test
    void usernameIsUnique() {
        User duplicateUser = MockData.getUser(U1, P2);
        assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(duplicateUser));
    }

    @Test
    void attemptToFindNonExistantId() {
        User user = MockData.getUser("user", "pass");
        System.out.println(user);
        assertThat(user.getId()).isNull();
    }
}