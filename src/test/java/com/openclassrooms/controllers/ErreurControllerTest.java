package com.openclassrooms.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@WebMvcTest(ErreurController.class)
class ErreurControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ErreurController erreurController;


    @Test
    void error400() throws Exception {
        mockMvc.perform(get("/400"))
                .andExpect(status().isOk())
                .andExpect(view().name("400"));
    }

    @Test
    void error403() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"));
    }

    @Test
    void error404() throws Exception {
        mockMvc.perform(get("/404"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }
}