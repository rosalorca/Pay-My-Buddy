package com.openclassrooms.repositories;


import com.openclassrooms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);
}
