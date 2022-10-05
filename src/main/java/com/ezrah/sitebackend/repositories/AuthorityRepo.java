package com.ezrah.sitebackend.repositories;

import com.ezrah.sitebackend.entities.Authority;
import com.ezrah.sitebackend.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
