package com.openclassrooms.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService us;
    @MockBean
    private UserRepository ur;

    @Test
    void getAllUsersTest() throws Exception {
        User user1 = new User();
        user1.setUserId(1);
        user1.setName("Özlem");
        User user2 = new User();
        user2.setUserId(2);
        user2.setName("Jack");
        when(us.getAllUsers()).thenReturn(List.of(user1, user2));
        String expected = "[User(userId=1, email=null, password=null, name=Özlem, lastname=null, accountList=null), User(userId=2, email=null, password=null, name=Jack, lastname=null, accountList=null)]";
        mockMvc.perform(get("/Users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Özlem"));


    }

    @Test
    void getUserByIdTest() throws Exception {
        User user1 = new User();
        user1.setUserId(1);
        when(us.getUserById(1)).thenReturn(Optional.of(user1));
        String expected = "[User(userId=1, email=null, password=null, name=Özlem, lastname=null, accountList=null), User(userId=2, email=null, password=null, name=Jack, lastname=null, accountList=null)]";
        mockMvc.perform(get("/User/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..userId").value(1));
        // .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId").value(2));
    }

    @Test
    void shouldCreateUserIfNotExist() throws Exception {
        when(us.getUserByEmailAndPassword("ozlem@gmail.com", "abcdef")).thenReturn(Optional.empty());
        User user1 = new User();
        user1.setUserId(1);
        user1.setEmail("ozlem@gmail.com");
        user1.setPassword("abcdef");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user1);
        mockMvc.perform(post("/User")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                /*.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("ozlem@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("abcdef"))*/
                .andReturn();


    }

    @Test
    void updateUserExistTest() throws Exception {
        User user1 = new User();
        user1.setUserId(1);
        user1.setName("Özlem");
        user1.setLastname("Dônder");
        when(us.getUserById(eq(1))).thenReturn(Optional.of(user1));
        String expected = "[User(userId=1, email=ozlem@gmail.com, password=abcdef, name=Özlem, lastname=Lorca, accountList=null), User(userId=2, email=null, password=null, name=Jack, lastname=null, accountList=null)]";
        User updateUser = new User();
        updateUser.setUserId(1);
        updateUser.setLastname("Lorca");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(updateUser);
        mockMvc.perform(put("/User/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname").value("Lorca"));


    }

    @Test
    void deleteUserExistTest() throws Exception {
    }
}