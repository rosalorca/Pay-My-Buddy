package com.openclassrooms.service;

import com.openclassrooms.model.Transaction;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transferRepository;


    public List<Transaction> getAllTransaction() {
        return transferRepository.findAll();
    }

    public Optional<Transaction> getTransactionsById(Integer id) {
        return transferRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transferRepository.save(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        Optional<Transaction> modified = transferRepository.findById(transaction.getTransactionId());
        if (modified.isPresent()) {
            transferRepository.save(transaction);
        }
    }

    public void deleteTransaction(Transaction transaction) {
        Optional<Transaction> removeOperation = transferRepository.findById(transaction.getTransactionId());
        if (removeOperation.isPresent()) {
            transferRepository.deleteById(transaction.getTransactionId());
        }
    }

    public Transaction createOperation(User debtor, User creditor, double amount, String description) {
        Transaction operation = new Transaction();
        var rate = 0.05;
        return operation;
    }

    public boolean processTransaction(Transaction transfer) {
        var amount = transfer.getAmount();
        if (amount <= 0) {

        }return false;
    }
}