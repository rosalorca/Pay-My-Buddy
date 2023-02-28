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
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String homePage(Model model) {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(springUser.getUsername());
        model.addAttribute("Name","Name :  "+ user.getName());
        model.addAttribute("Lastname","Lastname :  "+ user.getLastname());
        model.addAttribute("Email","Email :  "+ user.getEmail());
        model.addAttribute("Balance","Balance :  "+ user.getBalance());
        return "profile";
    }


}


