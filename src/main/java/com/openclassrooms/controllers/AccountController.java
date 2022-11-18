package com.openclassrooms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/ index")
    public String goHome(){
        return "index";
    }
    @GetMapping("/ login")
    public String login(){
        return "login";
    }
    @GetMapping("/ logout")
    public String logout(){
        return "login";
    }
}

