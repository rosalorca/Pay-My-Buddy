package com.openclassrooms.service;

import com.openclassrooms.model.Account;
import com.openclassrooms.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }
    public Account createAccount (Account account){
        return accountRepository.save(account);
    }

    public void updateAccount (Account account){
        Optional<Account> modified = accountRepository.findById(account.getAccountId());
        if(modified.isPresent()){
            accountRepository.save(account);
        }
    }
    public void deleteAccount (Account account){
        Optional<Account> removerAccount = accountRepository.findById(account.getAccountId());
        if (removerAccount.isPresent()){
            accountRepository.deleteById(account.getAccountId());
        }
        accountRepository.deleteById(account.getAccountId());
    }

}
