package com.openclassrooms.controllers;

import com.openclassrooms.model.User;
import com.openclassrooms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j

public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userHome() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(Model model) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(springUser.getUsername());
        model.addAttribute("user", user.getName() + " " + user.getLastname());
        return "home";
    }

}

