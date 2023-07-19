package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"})
@RestController
public class UserController {

    @PutMapping("/users/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(@RequestBody Credentials credentials) {
        System.out.println(credentials);
    }

    @PostMapping("/users/login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@RequestBody Credentials credentials) {
        System.out.println("Username: " + credentials.getUsername() + ", Password: " + credentials.getPassword());
    }

    @PatchMapping("/users/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@RequestBody Map<String, Credentials> details) {
        System.out.println(details);
    }
}
