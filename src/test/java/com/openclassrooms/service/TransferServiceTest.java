package com.openclassrooms.service;

import com.openclassrooms.model.Transfer;
import com.openclassrooms.repositories.TransferRepository;
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
public class TransferServiceTest {
    @MockBean
     private TransferRepository tr;
    @InjectMocks
    private TransferService ts;


    @Test
    void executeTransaction() {


    }



    @Test
    void getAllTransactionTest() {
        Transfer transaction = new Transfer();
        transaction.setTransactionId(1);
        when(tr.findAll()).thenReturn(List.of(transaction));

        List<Transfer> result = ts.getAllTransactions();
        assertEquals(1, result.size());

    }

    @Test
    void getTransactionsByIdTest() {
        Transfer acte = new Transfer();
        acte.setTransactionId(1);
        Optional<Transfer> transaction = Optional.of(acte);
        when(tr.findById(1)).thenReturn(transaction);

        Optional<Transfer> result = ts.getTransactionsById(1);
        assertEquals(1, result.get().getTransactionId());

    }


    @Test
    void updateTransactionTest() {
        Transfer acte = new Transfer();
        acte.setTransactionId(1);
        acte.setAmount(600);
        when(tr.findById(1)).thenReturn(Optional.of(acte));

        Optional<Transfer> result = Optional.of(acte);
        assertEquals(1,  result.get().getTransactionId());

        Transfer updateActe = new Transfer();
        updateActe.setTransactionId(1);
        updateActe.setAmount(200);

        ts.updateTransaction(updateActe);
        verify(tr,times (1)).save(updateActe);
    }

    @Test
    void deleteTransactionTest() {
        Transfer acte = new Transfer();
        acte.setTransactionId(1);
        acte.setAmount(600);

        when(tr.findById(1)).thenReturn(Optional.of(acte));

        Optional<Transfer> result = Optional.of(acte);
        assertEquals(1,  result.get().getTransactionId());

        ts.deleteTransaction(acte);
        verify(tr, times(1)).deleteById(1);
    }

}