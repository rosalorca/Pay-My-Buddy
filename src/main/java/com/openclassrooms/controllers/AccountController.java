package com.openclassrooms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/index")
    public String goHome(){
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}

