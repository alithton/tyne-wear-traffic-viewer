package com.afsmith.tyneweartrafficviewer.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void createUser() {
        User user = new User("username", "password", "salt");

        assertThat(user.getCredentials()).isNotNull();
        assertThat(user.getCredentials().getUsername()).isEqualTo("username");
    }

}