package com.openclassrooms.config;

import com.openclassrooms.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {
    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**/
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        System.out.println(username);
        return authentication;
    }

    @Override public boolean supports(Class<?> authentication) {
        return true;
    }
}
