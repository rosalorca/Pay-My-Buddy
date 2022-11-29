package com.openclassrooms.service;

import com.openclassrooms.model.Account;
import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Iterable<User> getAllUsers() {
        return userRepository.findAll();

    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public void updateUser(User user){
        Optional<User> modified = userRepository.findById(user.getUser_id());
        if(modified.isPresent()){
            userRepository.save(user);
        }
    }

    public void deleteUser(User user) {
        Optional<User> removeUser = userRepository.findByEmail(user.getEmail());
        if (removeUser.isPresent()) {
            userRepository.deleteById(user.getUser_id());
        }
    }
    public void addFriend(User user, User friendUser) {
       user.getFriendList().add(friendUser);
        userRepository.save(user);
    }

    public void removeFriend(User user, User friendUser) {
       user.getFriendList().remove(friendUser);
       userRepository.save(user);
    }
    public void addAccount (User user, Account account){
        user.getAccountList().add(account);
        userRepository.save(user);
    }
    public  void removeAccount (User user, Account account){
        user.getAccountList().remove(account);
        userRepository.save(user);
    }
    public void addTransfer (User user, Transfer transaction){
        user.getTransactionList().add(transaction);
        userRepository.save(user);
    }
    public void removeTransfer (User user, Transfer transaction){
        user.getTransactionList().remove(transaction);
        userRepository.save(user);
    }
}
