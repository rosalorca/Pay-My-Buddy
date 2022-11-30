package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home (Model model){
        model.addAttribute("message", "Welcome to your application PAy My Buddy ! ");

        return "home";
    }
   /* @RequestMapping("/admin/home")
    public String adminHome(Model model)
    {
        return "redirect:/user/list";
    }*/



}
