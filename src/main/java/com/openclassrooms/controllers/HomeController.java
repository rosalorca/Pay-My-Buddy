package com.openclassrooms.controllers;

import com.openclassrooms.model.User;
import com.openclassrooms.service.UserService;
import com.openclassrooms.webParams.TransactionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@Slf4j

public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(springUser.getUsername());
        model.addAttribute("user", user.getName() + " " + user.getLastname());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("transactionParams", new TransactionParams());
        return "home";
    }

    @PostMapping("/home/addMoney")
    public String addMoney(@ModelAttribute("transaction") TransactionParams transactionParams, Principal principal) {
        User me = userService.getUserByEmail(principal.getName());
        userService.addMoney(me, transactionParams.getAmount());
        return "redirect:/home?success";
    }

    @PostMapping("/home/removeMoney")
    public String removeMoney(@ModelAttribute("transaction") TransactionParams transactionParams, Principal principal) {
        User me = userService.getUserByEmail(principal.getName());
        userService.subtractMoney(me, transactionParams.getAmount());
        if(me.getBalance() > transactionParams.getAmount()){
            return "redirect:/home?removeSuccess";
        }else
        return "redirect:/home?removeFailed";
    }


}

