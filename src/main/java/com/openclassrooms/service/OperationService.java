package com.openclassrooms.service;

import com.openclassrooms.model.Operation;
import com.openclassrooms.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public Iterable<Operation> getOperations(){
        return operationRepository.findAll();
    }
    /*public Optional<Operation> getOperationsById (Integer id){
        return operationRepository.findById(id);
    }*/

}
