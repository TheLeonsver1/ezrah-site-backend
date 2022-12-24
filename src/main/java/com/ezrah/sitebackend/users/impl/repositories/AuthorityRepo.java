package com.ezrah.sitebackend.users.impl.repositories;

import com.ezrah.sitebackend.users.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
