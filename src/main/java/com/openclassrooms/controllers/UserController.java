package com.openclassrooms.controllers;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Success find all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") int id) {
        if (userService.getUserById(id).isPresent()) {
            User user = userService.getUserById(id).get();
            log.info("Success find user by id");
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        log.error("Can't find the user based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword()).isEmpty()) {
            userService.createUser(user);
            log.info("User created successfully", user.getEmail());
            return new ResponseEntity<>("User Created", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") int id, @RequestBody User user) {
        if (userService.getUserById(id).isPresent()) {
            user.setUserId(id);
            userService.updateUser(user);
            log.info("User updated successfully");
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        log.error("Failed to update user because the user was not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int id, @RequestBody User user) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(user);
            log.info("User deleted successfully");
            return new ResponseEntity<>("User deleted !", HttpStatus.OK);
        }
        log.error("Failed to delete user because of a BAD REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
