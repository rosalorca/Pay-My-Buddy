package com.openclassrooms.repository;

import com.openclassrooms.model.Count;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRepository extends CrudRepository<Count, Integer> {
}
