package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

//@Service
@Controller
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public  Optional<User> getUserByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public void updateUser(User user){
        Optional<User> modified = userRepository.findById(user.getUserId());
        if(modified.isPresent()){
            userRepository.save(user);
        }
    }

    public void deleteUser(User user) {
        Optional<User> removeUser = userRepository.findById(user.getUserId());
        if (removeUser.isPresent()) {
            userRepository.deleteById(user.getUserId());
        }
    }
    public void addFriend(User user, User myFriend) {
       user.getFriends().add(myFriend);
        userRepository.save(user);
    }

    public void removeFriend(User user, User friendUser) {
       user.getFriends().remove(friendUser);
       userRepository.save(user);
    }
  /*  public void addAccount (User user, Account account){
        user.getAccount();
        userRepository.save(user);
    }
    public  void removeAccount (User user, Account account){
        user.getAccountList().remove(account);
        userRepository.save(user);
    }*/
  /*  public void addTransfer (User user, Transaction transaction){
        user.getTransactionList().add(transaction);
        userRepository.save(user);
    }
    public void removeTransfer (User user, Transaction transaction){
        .remove(transaction);
        userRepository.save(user);
    }*/


}
