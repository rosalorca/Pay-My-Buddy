package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErreurController {

    @RequestMapping(value = "/400", method = RequestMethod.GET)
    public ModelAndView error400() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You have to enter good request, Sorry !";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("400");
        return mav;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView error403() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You have to enter good request please.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("404");
        return mav;
    }
}
