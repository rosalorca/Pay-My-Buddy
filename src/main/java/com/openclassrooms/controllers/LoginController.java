package com.openclassrooms.controllers;

import com.openclassrooms.model.LoginAndRegisterParams;
import com.openclassrooms.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class LoginController {
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public LoginController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginParams", new LoginAndRegisterParams());
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginParams") LoginAndRegisterParams request) {
        log.info(request.getEmail() + " " + request.getPassword());
        /*ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;*/
        return "home";
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }
    /*@RequestMapping("/*")
    public String getUserInfo(Principal user) {
        System.out.println("getUserInfo");
        StringBuffer userInfo = new StringBuffer();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUsernamePasswordLoginInfo(user));
        } else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOauth2LoginInfo(user));
        }
        return userInfo.toString();
    }*/


}
