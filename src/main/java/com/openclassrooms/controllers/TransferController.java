package com.openclassrooms.controllers;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.TransferRepository;
import com.openclassrooms.repositories.UserRepository;
import com.openclassrooms.service.TransferService;
import com.openclassrooms.service.UserService;
import com.openclassrooms.webParams.TransactionDetails;
import com.openclassrooms.webParams.TransactionParams;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class TransferController {
    @Autowired
    private TransferRepository transactionRepository;
    @Autowired
    private TransferService transactionService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransaction() {
        log.info("Success find all transaction");
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/transaction")
    public String transactionPage(Model model, Principal principal) {
        final User me = userService.getUserByEmail(principal.getName());
        List<User> users = me.getContacts();
        model.addAttribute("availableUsers", users);
        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        List<Transfer> myTransactions = transactionService.getAllTransactions().stream()
                .filter(transfer -> transfer.getUser1Id().equals(me.getUserId()))
                .collect(Collectors.toList());
        myTransactions.forEach(transfer -> {
            final TransactionDetails details = new TransactionDetails();
            details.setId(transfer.getTransactionId());
            details.setAmount(transfer.getAmount());
            details.setDescription(transfer.getDescription());
            Optional<User> optUser = userService.getUserById(transfer.getUser2Id());
            optUser.ifPresent(user -> details.setEmail(user.getName()));
            transactionDetailsList.add(details);
        });
        model.addAttribute("transactionParams", new TransactionParams());
        model.addAttribute("myTransaction", transactionDetailsList);
        return "transaction";
    }

    @PostMapping("/transaction")
    public String registerTransaction(@ModelAttribute("transaction") TransactionParams transactionParams, Principal principal) {
        transactionService.executeTransaction(principal, transactionParams);
            return "redirect:/transaction?success";

    }

    @GetMapping("/transaction/{transaction_id}") // tekrar bak
    public ResponseEntity<?> getTransactionById(@PathVariable("transId") int id) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            Transfer trans = transactionService.getTransactionsById(id).get();
            log.info("Success find transaction by id");
            return new ResponseEntity<>(trans, HttpStatus.OK);
        }
        log.error("Can't find the transaction based on this id");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping("/transaction/{transaction_id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("transId") int id, @RequestBody Transfer transaction) {
        if (transactionService.getTransactionsById(id).isPresent()) {
            transaction.setTransactionId(id);
            transactionService.updateTransaction(transaction);
            log.info("Transaction updated successfully");
            return new ResponseEntity<>("Transaction updated", HttpStatus.OK);
        }
        log.error("Failed to update transaction because the transaction was not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

   @GetMapping("/transaction/{transactionId}/delete")
    public String deleteTransaction (@PathVariable("transactionId") int id) {
       Optional<Transfer> action = transactionService.getTransactionsById(id);
       if(action.isPresent()) {
           transactionService.deleteTransaction(action.get());
       }
        log.error("Failed to delete transaction because of a BAD REQUEST");
        return "redirect:/transaction?transactionDeleteFailed";
    }


}
