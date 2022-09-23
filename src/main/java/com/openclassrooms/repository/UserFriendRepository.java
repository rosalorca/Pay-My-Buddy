package com.openclassrooms.repository;

import com.openclassrooms.model.UserFriend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendRepository extends CrudRepository<UserFriend, Integer> {
}
