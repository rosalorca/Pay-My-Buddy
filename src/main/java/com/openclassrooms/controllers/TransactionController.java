package com.openclassrooms.controllers;

import com.openclassrooms.model.Transaction;
import com.openclassrooms.repositories.TransactionRepository;
import com.openclassrooms.service.TransactionService;
import com.openclassrooms.webParams.TransactionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransaction() {
        log.info("Success find all transaction");
        return new ResponseEntity<>(transactionService.getAllTransaction(), HttpStatus.OK);
    }

    @GetMapping("/transaction")
    public String transactionPage(Model model) {
        model.addAttribute("transactionParams", new TransactionParams());
        return "transaction";
    }

    @PostMapping("/transaction")
    public String registerTransaction(@ModelAttribute("transaction") TransactionParams transactionParams, Principal principal) {
        transactionService.save(principal, transactionParams);
        return "redirect:/transaction?success";
    }

    @GetMapping("/transaction/{transaction_id}") // tekrar bak
    public ResponseEntity<?> getTransactionById(@PathVariable("transId") int id) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            Transaction trans = transactionService.getTransactionsById(id).get();
            log.info("Success find transaction by id");
            return new ResponseEntity<>(trans, HttpStatus.OK);
        }
        log.error("Can't find the transaction based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping("/transaction/{transaction_id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("transId") int id, @RequestBody Transaction transaction) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            transaction.setTransactionId(id);
            transactionService.updateTransaction(transaction);
            log.info("Transaction updated successfully");
            return new ResponseEntity<>("Transaction updated", HttpStatus.OK);
        }
        log.error("Failed to update transaction because the transaction was not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/transaction/{transaction_id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transId") int id, @RequestBody Transaction transaction) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            transactionService.deleteTransaction(transaction);
            log.info("Transaction deleted successfully");
            return new ResponseEntity<>("Transaction deleted", HttpStatus.OK);
        }
        log.error("Failed to delete transaction because of a BAD REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
