package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Credentials credentials;
    private String salt;
    private String token;
    private ZonedDateTime tokenExpiry;

    public User(String username, String password) {
        this.credentials =  new Credentials(username, password);
    }

    public User(String username, String password, String salt) {
        this(username, password);
        this.salt = salt;
    }

    public User(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Set a new token for the user with a specified duration.
     * @param token The new token.
     * @param duration The duration of the token in minutes.
     */
    public void setToken(String token, long duration) {
        this.token = token;
        ZonedDateTime currentTime = ZonedDateTime.now();
        this.tokenExpiry = currentTime.plusMinutes(duration);
    }
}
