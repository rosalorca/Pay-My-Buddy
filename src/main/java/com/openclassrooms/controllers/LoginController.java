package com.openclassrooms.controllers;

import com.openclassrooms.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private IUserRepository userRepository;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public LoginController(org.springframework.security.oauth2.client.OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/admin")
    @RolesAllowed({"USER", "ADMIN"})
    public String getAdmin() {
        return "Welcome Admin !";
    }
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }
    @GetMapping("400")
    public ModelAndView error400() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested .";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("400");
        return mav;
    }

    @GetMapping("403")
    public ModelAndView error403() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
    @GetMapping("404")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You have to enter good request please.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("404");
        return mav;
    }


    @RequestMapping("/*")
    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUsernamePasswordLoginInfo(user));
        } else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOauth2LoginInfo(user));
        }
        return userInfo.toString();
    }

    private StringBuffer getOauth2LoginInfo(Principal user) {
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();

        OAuth2AuthorizedClient authClient = this.oAuth2AuthorizedClientService
                .loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
        Map<String, Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
        String userToken = authClient.getAccessToken().getTokenValue();
        protectedInfo.append("Welcome, " + userDetails.get("name") + "<br><br>");
        protectedInfo.append("email: " + userDetails.get("email") + "<br><br>");
        protectedInfo.append("Access Token: " + userToken + "<br><br>");

        OidcIdToken idToken = getIdToken(principal);

        if (idToken != null) {
            protectedInfo.append("idToken value: " + idToken.getTokenValue() + "<br><br>");
            protectedInfo.append("Token mapped values <br><br>");
            Map<String, Object> claims = idToken.getClaims();
            for (String key : claims.keySet()) {
                protectedInfo.append("     " + key + " :      " + claims.get(key) + "<br>");
            }
        }
        return protectedInfo;
    }

    private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
        StringBuffer usernameInfo = new StringBuffer();
        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if (token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            usernameInfo.append("Welcome, " + u.getUsername());
        } else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    private OidcIdToken getIdToken(OAuth2User principal) {
        if (principal instanceof DefaultOAuth2User) {
            DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
            return oidcUser.getIdToken();
        }
        return null;
    }
}
