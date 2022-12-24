package com.ezrah.sitebackend.users.impl.repositories;

import com.ezrah.sitebackend.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);
}
