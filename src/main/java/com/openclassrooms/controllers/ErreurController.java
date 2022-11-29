package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErreurController {

    @GetMapping("/400")
    public ModelAndView error400() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You have to enter good request, Sorry !";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("400");
        return mav;
    }

    @GetMapping("/403")
    public ModelAndView error403() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
    @GetMapping("/404")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You have to enter good request please.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("404");
        return mav;
    }
}
