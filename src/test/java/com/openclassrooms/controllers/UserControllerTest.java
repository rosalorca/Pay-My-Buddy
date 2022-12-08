package com.openclassrooms.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void home() throws Exception {
    }

    @Test
    void getAllUsers() throws Exception {
    }

    @Test
    void getUserById() throws Exception {
    }

    @Test
    void createUser() throws Exception {
    }

    @Test
    void updateUser()throws Exception  {
    }

    @Test
    void deleteUser() throws Exception {
    }
}