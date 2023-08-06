package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * Represents user authentication data.
 */
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

    /**
     * Constructor for users using the username and password.
     * @param username The username of the user.
     * @param password The hash of the user's password.
     */
    public User(String username, String password) {
        this.credentials =  new Credentials(username, password);
    }

    /**
     * Constructor for user data.
     * @param username The user's username.
     * @param password The hash of the user's password.
     * @param salt The salt used to generate the password hash.
     */
    public User(String username, String password, String salt) {
        this(username, password);
        this.salt = salt;
    }

    /**
     * Constructor for user data.
     * @param credentials The user's authentication credentials.
     */
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
