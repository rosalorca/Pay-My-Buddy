package com.openclassrooms.controllers;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@Slf4j
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Model model) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profile = userService.getUserByEmail(springUser.getUsername());
        model.addAttribute("profile", profile.getName());
        model.addAttribute("profile", profile.getLastname());
        model.addAttribute("profile", profile.getEmail());
        model.addAttribute("profile", profile.getPassword());
        return "profile";
    }

}
