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

    public Transaction createTransaction(Transaction operation) {
        return transferRepository.save(operation);
    }

    public void updateTransaction(Transaction operation) {
        Optional<Transaction> modified = transferRepository.findById(operation.getTransId());
        if (modified.isPresent()) {
            transferRepository.save(operation);
        }
    }

    public void deleteTransaction(Transaction operation) {
        Optional<Transaction> removeOperation = transferRepository.findById(operation.getTransId());
        if (removeOperation.isPresent()) {
            transferRepository.deleteById(operation.getTransId());
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