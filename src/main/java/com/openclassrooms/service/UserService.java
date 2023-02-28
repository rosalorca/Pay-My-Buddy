package com.openclassrooms.service;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    public void addTransaction(final User user1, final User user2, final Transfer transaction) {
        userRepository.save(user1);
        userRepository.save(user2);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        Optional<User> modified = userRepository.findById(user.getUserId());
        if (modified.isPresent()) {
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
        user.getContacts().add(myFriend);
        userRepository.save(user);
    }

    public void removeFriend(User user, User friendUser) {
        user.getContacts().remove(friendUser);
        userRepository.save(user);
    }

    public boolean addMoney(User user, double amount) {
        if (amount <= 0) {
            log.error("Cannot add 0 nor negative amount {}", amount);
            return false;
        }
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return true;
    }

    public boolean subtractMoney(User user, double amount) {
        if (amount <= 0) {
            log.error("Cannot subtract 0 nor negative amount {}", amount);
            return false;
        }
        if (user.getBalance() < amount) {
            log.error("User has not enough money to subtract {}, current balance is {}", amount, user.getBalance());
            return false;
        }
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);
        return true;
    }

}
