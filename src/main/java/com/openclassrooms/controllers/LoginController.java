package com.openclassrooms.controllers;

import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.webParams.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginParams", new LoginParams());
        return "login";
    }
   /* @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }*/
}
