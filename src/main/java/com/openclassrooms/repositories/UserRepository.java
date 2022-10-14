package com.openclassrooms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
