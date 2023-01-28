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
    public RegistrationParams userRegistrationDto() {
        return new RegistrationParams();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationParams registrationDto) {
        us.save(registrationDto);
        return "redirect:/registration?success";
    }
}


