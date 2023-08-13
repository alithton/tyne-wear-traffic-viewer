package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.services.UserService;
import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.NotAuthenticatedException;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * A REST controller for the user data API.
 */
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"}, allowCredentials = "true")
@RestController
public class UserController {
    private final UserService userService;

    /**
     * Attempt to register as a user with the provided credentials. If a user with
     * the same username already exists, return an HTTP 409 Conflict response. If
     * registration is successful, return a 201 Created response with an HTTP-only
     * cookie containing the user's authentication token for the current session.
     *
     * @param credentials The username and password with which to register.
     * @return An HTTP response.
     */
    @PostMapping("/users/signup")
    public ResponseEntity<?> signUp(@RequestBody Credentials credentials) {
        String token;
        try {
            token = userService.save(credentials);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");
        }
        HttpHeaders headers = getHeadersWithToken(token);
        return new ResponseEntity<>("", headers, HttpStatus.CREATED);
    }

    /**
     * Attempt to log in with the provided credentials.
     * <p>
     *     If no user matching the provided credentials is found, return an HTTP
     *     401 Unauthorised response. If registration is successful, return a 201
     *     Created response with an HTTP-only cookie containing the user's authentication
     *     token for the current session.
     * </p>
     * @param credentials The provided username and password.
     * @return An HTTP response.
     */
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        User user = userService.find(credentials);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user found with those credentials.");
        }
        return ResponseEntity.noContent()
                             .header(HttpHeaders.SET_COOKIE, createAuthCookie(user.getToken()))
                             .build();
    }

    /**
     * Attempt to update the credentials of the user specified by the provided authentication
     * token using the provided new credentials.
     * <p>
     *     If no user matching the provided token is found, return an HTTP
     *     401 Unauthorised response.
     * </p>
     * @param credentials The new username and password for the user.
     * @param token An authentication token.
     */
    @PutMapping("/users/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@RequestBody Credentials credentials, @CookieValue("token") String token) {
        User user;
        try {
            user = userService.findByToken(token);
        }
        catch (NotAuthenticatedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        userService.update(user, credentials);
    }

    /*
     * Get HTTP headers on which a cookie containing the provided authentication
     * token has been set.
     */
    private HttpHeaders getHeadersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, createAuthCookie(token));
        return headers;
    }

    /*
     * Create an HTTP-only cookie containing the provided authentication token.
     */
    private String createAuthCookie(String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                                              .httpOnly(true)
                                              .maxAge(3600L)
                                              .path("/")
                                              .build();
        return cookie.toString();
    }
}
