package com.afsmith.tyneweartrafficviewer.business.controllers;

import com.afsmith.tyneweartrafficviewer.business.services.UserService;
import com.afsmith.tyneweartrafficviewer.entities.Credentials;
import com.afsmith.tyneweartrafficviewer.exceptions.UserAlreadyExistsException;
import com.afsmith.tyneweartrafficviewer.util.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    Credentials mockCredentials = new Credentials("user1", "password");

    @Test
    void signup() throws Exception {
        mockMvc.perform(post("/users/signup")
                                .content(mapper.writeValueAsString(mockCredentials))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void signupAlreadyExists() throws Exception {
        doThrow(UserAlreadyExistsException.class).when(userService).save(any(Credentials.class));

        mockMvc.perform(post("/users/signup")
                                .content(mapper.writeValueAsString(mockCredentials))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void loginWithValidCredentials() throws Exception {
        when(userService.find(mockCredentials))
                .thenReturn(MockData.getUser("user", "password"));

        mockMvc.perform(post("/users/login")
                                .content(mapper.writeValueAsString(mockCredentials))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void loginWithInvalidCredentials() throws Exception {
        when(userService.find(any(Credentials.class))).thenReturn(null);

        mockMvc.perform(post("/users/login")
                                .content(mapper.writeValueAsString(mockCredentials))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isUnauthorized());
    }


}