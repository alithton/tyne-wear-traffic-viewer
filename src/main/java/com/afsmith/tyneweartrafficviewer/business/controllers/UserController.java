package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.services.UserService;
import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import com.afsmith.tyneweartrafficviewer.entities.User;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"}, allowCredentials = "true")
@RestController
public class UserController {
    private final UserService userService;

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

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        System.out.println("Username: " + credentials.getUsername() + ", Password: " + credentials.getPassword());
        User user = userService.find(credentials);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user found with those credentials.");
        }
        return ResponseEntity.noContent()
                             .header(HttpHeaders.SET_COOKIE, createAuthCookie(user.getToken()))
                             .build();
    }

    @PutMapping("/users/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@RequestBody Credentials credentials, @CookieValue("token") String token) {
        System.out.println(token);
        User user = userService.findByToken(token);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user found with a valid token matching the one provided.");
        }
        userService.update(user, credentials);
    }

    private HttpHeaders getHeadersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, createAuthCookie(token));
        return headers;
    }

    private String createAuthCookie(String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                                              .httpOnly(true)
                                              .maxAge(3600L)
                                              .build();
        return cookie.toString();
    }
}
