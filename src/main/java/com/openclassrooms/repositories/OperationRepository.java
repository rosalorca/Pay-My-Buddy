package com.openclassrooms.repositories;

import com.openclassrooms.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
}
