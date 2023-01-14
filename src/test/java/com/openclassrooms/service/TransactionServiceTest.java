package com.openclassrooms.service;

import com.openclassrooms.model.Transaction;
import com.openclassrooms.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @MockBean
     private TransactionRepository tr;
    @InjectMocks
    private TransactionService ts;


    @Test
    void getAllTransactionTest() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        when(tr.findAll()).thenReturn(List.of(transaction));

        List<Transaction> result = ts.getAllTransaction();
        assertEquals(1, result.size());


    }

    @Test
    void getTransactionsByIdTest() {
        Transaction acte = new Transaction();
        acte.setTransactionId(1);
        Optional<Transaction> transaction = Optional.of(acte);
        when(tr.findById(1)).thenReturn(transaction);

        Optional<Transaction> result = ts.getTransactionsById(1);
        assertEquals(1, result.get().getTransactionId());

    }

    @Test
    void createTransactionTest() {
        Transaction acte = new Transaction();
        acte.setTransactionId(1);
        acte.setAmount(600);
        ts.createTransaction(acte);
        verify(tr,times (1)).save(acte);

    }

    @Test
    void updateTransactionTest() {
        Transaction acte = new Transaction();
        acte.setTransactionId(1);
        acte.setAmount(600);
        when(tr.findById(1)).thenReturn(Optional.of(acte));

        Optional<Transaction> result = Optional.of(acte);
        assertEquals(1,  result.get().getTransactionId());

        Transaction updateActe = new Transaction();
        updateActe.setTransactionId(1);
        updateActe.setAmount(200);

        ts.updateTransaction(updateActe);
        verify(tr,times (1)).save(updateActe);
    }

    @Test
    void deleteTransactionTest() {
        Transaction acte = new Transaction();
        acte.setTransactionId(1);
        acte.setAmount(600);

        when(tr.findById(1)).thenReturn(Optional.of(acte));

        Optional<Transaction> result = Optional.of(acte);
        assertEquals(1,  result.get().getTransactionId());

        ts.deleteTransaction(acte);
        verify(tr, times(1)).deleteById(1);
    }

    @Test
    void createOperationTest() {
    }

    @Test
    void processTransactionTest() {
    }
}