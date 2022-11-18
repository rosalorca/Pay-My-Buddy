package com.openclassrooms.service;

import com.openclassrooms.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserDetailsService implements UserService{

    @Autowired
    IUserRepository userRepository =  ;


}
