package com.openclassrooms.controllers;

import com.openclassrooms.service.MyUserDetailService;
import com.openclassrooms.webParams.RegistrationParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private MyUserDetailService us;

    public RegistrationController(MyUserDetailService userService) {
        super();
        this.us = userService;
    }

    @ModelAttribute("user")
    public RegistrationParams userRegistration() {
        return new RegistrationParams();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationParams registration) {
        us.save(registration);
        return "redirect:/registration?success";
    }
}


