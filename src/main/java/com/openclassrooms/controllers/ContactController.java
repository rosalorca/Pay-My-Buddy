package com.openclassrooms.controllers;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.UserService;
import com.openclassrooms.webParams.ContactParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@Slf4j
public class ContactController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/contact")
    public String contactPage(Model model, Principal principal) {
        final User me = userService.getUserByEmail(principal.getName());
        List<User> users = userService.getAllUsers();
        users.removeIf(user -> Objects.equals(user.getUserId(), me.getUserId()) || me.getContacts().contains(user));
        model.addAttribute("availableUsers", users);
        model.addAttribute("userId", me.getUserId());
        model.addAttribute("contactParams", new ContactParams());
        model.addAttribute("myContacts", me.getContacts());
        return "contact";
    }
    @PostMapping("/contact")
    public String registerContact(@ModelAttribute("contact") ContactParams contactParams, Principal principal) {
        userService.addFriend(
                userService.getUserByEmail(principal.getName()), // my user
                userService.getUserByEmail(contactParams.getEmail()) // my friend user to add
        );
        return "redirect:/contact?success";
    }
    @PutMapping("contact/{userId}/addFriend")
    public ResponseEntity<?> addFriend (@PathVariable("userId") int id, @RequestBody User friend){
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()){
            userService.addFriend(user.get(), friend);
            log.info("Friend added successfully");
            return new ResponseEntity<>("Friends added ! ", HttpStatus.OK);
        }
        log.error("Can't find the user based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    @GetMapping("/contact/myFriendList/{userId}/deleteFriend/{friendId}")
    public String deleteFriend (@PathVariable ("userId") int id, @PathVariable ("friendId") int friendId) {
        Optional<User> user = userService.getUserById(id);
        Optional<User> friend = userService.getUserById(friendId);
        if (user.isPresent() && friend.isPresent()) {
            userService.removeFriend(user.get(), friend.get());
            log.info("Friend deleted successfully");
            return "redirect:/contact?friendDeleteSuccess";
        }
        log.error("Can't find the user based on this id");
        return "redirect:/contact?friendDeleteFailed";
    }
}
