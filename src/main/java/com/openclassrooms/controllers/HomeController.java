package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        System.out.println(principal);
        model.addAttribute("message", "Welcome to your application Pay My Buddy ! ");
        return "home";
    }

}
