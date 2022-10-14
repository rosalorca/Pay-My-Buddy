package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Iterable<User> getUsers (){
        return userRepository.findAll();
    }
    public Optional<User> getUsersById(Integer id){
        return userRepository.findById(id);

    }
}
