package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.webParams.RegistrationParams;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailService extends UserDetailsService {
    User save (RegistrationParams registrationParams);
}
