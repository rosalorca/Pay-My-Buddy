package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UserServiceImpl extends UserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }
}
