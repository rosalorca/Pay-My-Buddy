package com.openclassrooms.controllers;

import com.openclassrooms.model.LoginAndRegisterParams;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository ur;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("registration", "user", new LoginAndRegisterParams());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user") LoginAndRegisterParams userRegistrationObject) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());

        User user = new User();

        return  null;
    }


}


