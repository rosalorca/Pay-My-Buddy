package com.openclassrooms.service;

import com.openclassrooms.model.Account;
import com.openclassrooms.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @MockBean
    private AccountRepository ar;

    @InjectMocks
    private AccountService as = new AccountService();

    @Test
    void getAccountsTest() {
        Account account = new Account();
        account.setAccountId(1);
        account.setBalance(100);
        when(ar.findAll()).thenReturn(List.of(account));

        List<Account> result = as.getAccounts();
        assertEquals(1, result.size());
    }

    @Test
    void getAccountsByIdTest() {
        Account account = new Account();
        account.setAccountId(1);
        account.setBalance(100);
        Optional<Account> debt = Optional.of(account);
        when(ar.findById(1)).thenReturn(debt);

        Optional<Account> result = as.getAccountById(1);
        assertEquals(1, result.get().getAccountId());
    }

    @Test
    void createAccountTest() {
        Account account = new Account();
        account.setAccountId(1);
        account.setBalance(100);
        as.createAccount(account);
        verify(ar,times (1)).save(account);
    }

  /*  @Test
    void updateAccountTest() {
        Account account = new Account();
        account.setAccountId(1);
        account.setBalance(100);
        when(ar.findAll()).thenReturn(List.of(account));


        List<Account> result = as.getAccounts();
        assertEquals(1, result.size());

        Account updateAccount = new Account();
        updateAccount.setAccountId(1);
        updateAccount.setBalance(200);

        as.updateAccount(updateAccount);
        verify(ar,times (1)).save(updateAccount);

    }

    @Test
    void deleteAccountTest() {
        Account account = new Account();
        account.setAccountId(1);
        account.setBalance(100);

        when(ar.findById(1)).thenReturn(Optional.of(account));

        Optional<Account> result = Optional.of(account);
        assertEquals(1,  result.get().getAccountId());

        as.deleteAccount(account);
        verify(ar, times(1)).deleteById(1);
    }*/
}