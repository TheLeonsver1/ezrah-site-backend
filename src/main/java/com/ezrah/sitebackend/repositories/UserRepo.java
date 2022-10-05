package com.ezrah.sitebackend.repositories;

import com.ezrah.sitebackend.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);
}
