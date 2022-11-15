package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class UserService {
    @Autowired
    private IUserRepository userRepository;


    public Iterable<User> getUsers (){
        return userRepository.findAll();

    }
    public Optional<User> getUsersById(Integer id){
        return userRepository.findById(id);
    }
    public User saveUser (User user){
        return userRepository.save(user);
    }
    public void deleteUserById (Integer id){
        userRepository.deleteById(id);
    }


}
