package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    private User rosa;
    private UserService userService ;


    @BeforeAll
    private static void setUp() {
        UserService userService = new UserService();
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void saveUser() {
        rosa = userRepository.save(rosa);
        Assert.assertNotNull(rosa.getUser_id());
        Assert.assertEquals(rosa.getUser_id(),1,1);
    }

    @Test
    void deleteUserById() {
        Integer id = rosa.getUser_id();
        userRepository.delete(rosa);
        Optional<User> user = userRepository.findById(id);
        Assert.assertFalse(user.isPresent());

    }
}