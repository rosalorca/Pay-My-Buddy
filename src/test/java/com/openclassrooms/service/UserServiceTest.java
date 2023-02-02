package com.openclassrooms.service;

import com.openclassrooms.model.User;
import com.openclassrooms.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository ur;

    @InjectMocks
    private UserService us = new UserService();

    @Test
    void getAllUsersTest() {

        User user1 = new User();
        user1.setName("Özlem");
        User user2 = new User();
        user2.setName("Jack");
        when(ur.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = us.getAllUsers();
        assertEquals("Özlem", result.get(0).getName());
        assertEquals("Jack", result.get(1).getName());
    }

    @Test
    void getUserByIdTest() throws Exception {
        User ozlem = new User();
        ozlem.setUserId(1);
        Optional<User> user = Optional.of(ozlem);
        when(ur.findById(1)).thenReturn(user);
        Optional<User> result = us.getUserById(1);
        assertEquals(1, result.get().getUserId());

    }

    @Test
    void getUserByEmailTest() {
        User userOzlem = new User();
        userOzlem.setEmail("ozlem@gmail.com");
        userOzlem.setPassword("abcdef");
        Optional<User> user = Optional.of(userOzlem);
        when(ur.findByEmailAndPassword(eq("ozlem@gmail.com"), eq("abcdef"))).thenReturn(user);

        Optional<User> result = us.getUserByEmailAndPassword("ozlem@gmail.com", "abcdef");
        assertTrue(result.isPresent());
        assertEquals("ozlem@gmail.com", result.get().getEmail());
        assertEquals("abcdef", result.get().getPassword());
    }

    @Test
    void createUserTest() {
        User userOzlem = new User();
        userOzlem.setEmail("ozlem@gmail.com");
        userOzlem.setPassword("abcdef");
        us.createUser(userOzlem);
        verify(ur, times(1)).save(userOzlem);
    }

    @Test
    void updateUserExistTest() throws Exception {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setEmail("ozlem@gmail.com");
        ozlem.setLastname("Donder");
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Optional<User> result = us.getUserById(1);
        assertEquals("Donder", result.get().getLastname());

        User updateOzlem = new User();
        updateOzlem.setUserId(1);
        updateOzlem.setLastname("Lorca");
        updateOzlem.setEmail("ozlem@yahoo.com");

        us.updateUser(updateOzlem);
        verify(ur, times(1)).save(updateOzlem);

    }

    @Test
    void updateUserNotExistTest() throws Exception {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setEmail("ozlem@gmail.com");
        ozlem.setLastname("Donder");
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Optional<User> result = us.getUserById(1);
        assertEquals("Donder", result.get().getLastname());

        User updateOzlem = new User();
        updateOzlem.setUserId(1);
        updateOzlem.setLastname("Lorca");
        updateOzlem.setEmail("ozlem@yahoo.com");

        us.updateUser(updateOzlem);
        assertTrue(us.getUserById(10).isEmpty());
    }

    @Test
    void deleteUserTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setEmail("ozlem@gmail.com");
        ozlem.setLastname("Donder");
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Optional<User> result = us.getUserById(1);
        assertEquals("Donder", result.get().getLastname());

        us.deleteUser(ozlem);
        verify(ur, times(1)).deleteById(1);
    }

    @Test
    void addFriendTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setFriends(new ArrayList<>());
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        User kevin = new User();
        ozlem.setUserId(2);
        when(ur.findById(eq(2))).thenReturn(Optional.of(kevin));

        List<User> ozlemFriends = ozlem.getFriends();
        assertTrue(ozlemFriends.isEmpty());
        us.addFriend(ozlem, kevin);
        ozlemFriends = ozlem.getFriends();
        assertEquals(1, ozlemFriends.size());
        assertEquals(kevin, ozlemFriends.get(0));

    }

    @Test
    void removeFriendTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setFriends(new ArrayList<>());
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        User kevin = new User();
        ozlem.setUserId(2);
        when(ur.findById(eq(2))).thenReturn(Optional.of(kevin));

        List<User> ozlemFriends = ozlem.getFriends();
        assertTrue(ozlemFriends.isEmpty());
        us.addFriend(ozlem, kevin);
        ozlemFriends = ozlem.getFriends();
        assertEquals(1, ozlemFriends.size());
        assertEquals(kevin, ozlemFriends.get(0));

        us.removeFriend(ozlem, kevin);
        assertEquals(0, ozlemFriends.size());

    }

    @Test
    void addAccountTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setAccounts(new ArrayList<>());
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Account account = new Account();
        account.setAccountId(1);

        List<Account> ozlemAccount = ozlem.getAccounts();
        assertTrue(ozlemAccount.isEmpty());

        us.addAccounts(ozlem, account);
        ozlemAccount = ozlem.getAccounts();
        assertEquals(1, ozlemAccount.size());
        assertEquals(account, ozlemAccount.get(0));


    }

    @Test
    void removeAccountTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setAccounts(new ArrayList<>());
        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Account account = new Account();
        account.setAccountId(1);

        List<Account> ozlemAccount = ozlem.getAccounts();
        assertTrue(ozlemAccount.isEmpty());

     //   us.addAccount(ozlem, account);
        ozlemAccount = ozlem.getAccounts();
        assertEquals(1, ozlemAccount.size());
        assertEquals(account, ozlemAccount.get(1));

        //us.removeAccount(ozlem, account);
        assertEquals(0, ozlemAccount.size());
    }

 /*   @Test
    void addTransferTest() {
        User ozlem = new User();
        ozlem.setUserId(1);
        ozlem.setTransaction(new ArrayList<>());

        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Transaction transaction = new Transaction();
        transaction.setTransId(1);

        List<Transaction> ozlemTransaction = ozlem.getTransactionList();
        assertTrue(ozlemTransaction.isEmpty());

        us.addTransfer(ozlem, transaction);
        ozlemTransaction = ozlem.getTransactionList();
        assertEquals(1, ozlemTransaction.size());
        assertEquals(transaction, ozlemTransaction.get(0));
    }

    @Test
    void removeTransferTest() {
        User ozlem = new User(userRegistrationObject.getEmail(), encodedPassword, authorities);
        ozlem.setUserId(1);
        ozlem.setTransactionList(new ArrayList<>());

        when(ur.findById(eq(1))).thenReturn(Optional.of(ozlem));

        Transaction transaction = new Transaction();
        transaction.setTransId(1);

        List<Transaction> ozlemTransaction = ozlem.getTransactionList();
        assertTrue(ozlemTransaction.isEmpty());

        us.addTransfer(ozlem, transaction);
        ozlemTransaction = ozlem.getTransactionList();
        assertEquals(1, ozlemTransaction.size());
        assertEquals(transaction, ozlemTransaction.get(0));

        us.removeTransfer(ozlem, transaction);
        assertEquals(0, ozlemTransaction.size());
    }*/
}