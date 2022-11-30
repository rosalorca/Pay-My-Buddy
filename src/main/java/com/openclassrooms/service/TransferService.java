package com.openclassrooms.service;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.model.User;
import com.openclassrooms.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;


    public Iterable<Transfer> getAllTransactions() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransactionsById(Integer id) {
        return transferRepository.findById(id);
    }

    public Transfer createTransaction(Transfer operation) {
        return transferRepository.save(operation);
    }

    public void updateTransaction(Transfer operation) {
        Optional<Transfer> modified = transferRepository.findById(operation.getTransfer_id());
        if (modified.isPresent()) {
            transferRepository.save(operation);
        }
    }

    public void deleteTransaction(Transfer operation) {
        Optional<Transfer> removeOperation = transferRepository.findById(operation.getTransfer_id());
        if (removeOperation.isPresent()) {
            transferRepository.deleteById(operation.getTransfer_id());
        }
    }

    public Transfer createTransaction(User debtor, User creditor, double amount, String description) {
        Transfer operation = new Transfer();
        var rate = 0.05;
        return operation;
    }

    public boolean processTransaction(Transfer transfer) {
        var amount = transfer.getAmount();
        if (amount <= 0) {

        }return false;
    }
}