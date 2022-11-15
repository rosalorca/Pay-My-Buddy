package com.openclassrooms.service;

import com.openclassrooms.model.Account;
import com.openclassrooms.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository accountRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }
    public Optional<Account> getAccountsById (Integer id){
        return accountRepository.findById(id);
    }
    public Account saveAccount (Account account){
        return accountRepository.save(account);
    }
    public void deleteAccountById (Integer id){
        accountRepository.deleteById(id);
    }
}
