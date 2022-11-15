package com.openclassrooms.service;

import com.openclassrooms.model.Operation;
import com.openclassrooms.repositories.IOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {
    @Autowired
    private IOperationRepository operationRepository;

    public Iterable<Operation> getOperations(){
        return operationRepository.findAll();
    }
    public Optional<Operation> getOperationsById (Integer id){
        return operationRepository.findById(id);
    }
    public Operation saveOperation (Operation operation){
        return operationRepository.save(operation);
    }

    public void deleteOperationById (Integer id){
        operationRepository.deleteById(id);
    }
}
