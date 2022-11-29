package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model)
    {
        return "home";
    }
   /* @RequestMapping("/admin/home")
    public String adminHome(Model model)
    {
        return "redirect:/user/list";
    }*/



}
