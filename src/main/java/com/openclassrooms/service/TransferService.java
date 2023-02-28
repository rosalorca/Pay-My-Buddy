package com.openclassrooms.service;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.TransferRepository;
import com.openclassrooms.webParams.TransactionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private UserService userService;

    public void executeTransaction(Principal principal, TransactionParams transactionParams) {
        final Transfer transaction = new Transfer();
        User user1 = userService.getUserByEmail(principal.getName());
        User user2 = userService.getUserByEmail(transactionParams.getEmail());
        transaction.setUser1Id(user1.getUserId());
        transaction.setUser2Id(user2.getUserId());
        transaction.setAmount(transactionParams.getAmount());
        transaction.setDescription(transactionParams.getDescription());
        transaction.setDate(LocalDate.now());

        final boolean hasSubtractedMoney = userService.subtractMoney(user1, transaction.getAmount());
        if (hasSubtractedMoney) {
            userService.addMoney(user2, transactionParams.getAmount());
            transferRepository.save(transaction);
        }
    }

  /* public void commissionTransaction(TransactionParams transactionParams) {
        double rate = 0.05;
        double amount = 0;
        double fare = amount * rate;
        double bank =

    }*/


    public List<Transfer> getAllTransactions() {
        return transferRepository.findAll();
    }


    public List<Transfer> getAllTransactionsOfUser(final Integer userId) {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransactionsById(Integer id) {
        return transferRepository.findById(id);
    }

    public void updateTransaction(Transfer transaction) {
        Optional<Transfer> modified = transferRepository.findById(transaction.getTransactionId());
        if (modified.isPresent()) {
            transferRepository.save(transaction);
        }
    }

    public void deleteTransaction(Transfer transaction) {
        Optional<Transfer> removeOperation = transferRepository.findById(transaction.getTransactionId());
        if (removeOperation.isPresent()) {
            transferRepository.deleteById(transaction.getTransactionId());
        }
    }

}