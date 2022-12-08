package com.openclassrooms.controllers;

import com.openclassrooms.model.Account;
import com.openclassrooms.repositories.AccountRepository;
import com.openclassrooms.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/accounts")
    public String account(Model model) {
        model.addAttribute("account", accountRepository.findAll());
        return "account";
    }
    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        log.info("Success find all account");
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable("accountId") int id) {
        final Optional<Account> optAccount = accountService.getAccountById(id);
        if (optAccount.isPresent()) {
            final Account account = optAccount.get();
            log.info("Success find account by id");
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        log.error("Can't find the account based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/account")
    public ResponseEntity<?> createAccount (@RequestBody Account account) {
       accountService.createAccount(account);
            return new ResponseEntity<>("Account created a account successfully ! ", HttpStatus.CREATED);

    }
    @PutMapping("/account/{accountId}")
    public ResponseEntity<?> updateAccount (@PathVariable("accountId") int id, @RequestBody Account account){
        if(accountService.getAccountById(id).isPresent()){
            account.setAccountId(id);
            accountService.updateAccount(account);
            return new ResponseEntity<>(" Account updated successfully", HttpStatus.OK);
        }
        log.error("Failed to update bank account because the bank account was not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable ("accountId") int id,@RequestBody Account account){
        if(accountService.getAccountById(id).isPresent()){
            accountService.deleteAccount(account);
            log.info("Account deleted successfully");
            return new ResponseEntity<>("Account deleted", HttpStatus.OK);
        }
        log.error("Failed to delete account because of a BAD REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}