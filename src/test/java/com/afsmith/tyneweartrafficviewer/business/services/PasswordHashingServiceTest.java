package com.afsmith.tyneweartrafficviewer.business.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordHashingServiceTest {

    PasswordHashingService hashingService = new PasswordHashingService();
    String PASSWORD = "password";

    @Test
    void getSalt() {
        String salt = hashingService.getSalt();
        String salt2 = hashingService.getSalt();
        assertThat(salt).isNotEmpty();
        assertThat(salt).isNotEqualTo(salt2);
    }

    @Test
    void hashPasswordSucceeds() {
        String salt = hashingService.getSalt();
        String hash = hashingService.hash(PASSWORD, salt);

        assertThat(hash).isNotEmpty();
    }

    @Test
    void samePasswordAndSaltYieldsSameHash() {
        String salt = hashingService.getSalt();

        String hash1 = hashingService.hash(PASSWORD, salt);
        String hash2 = hashingService.hash(PASSWORD, salt);

        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void differentSaltsYieldDifferentHashes() {
        String hash1 = hashingService.hash(PASSWORD, hashingService.getSalt());
        String hash2 = hashingService.hash(PASSWORD, hashingService.getSalt());

        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    void differentPasswordsYieldDifferentHashes() {
        String salt = hashingService.getSalt();

        String hash1 = hashingService.hash(PASSWORD, salt);
        String hash2 = hashingService.hash("password2", salt);

        assertThat(hash1).isNotEqualTo(hash2);
    }

}