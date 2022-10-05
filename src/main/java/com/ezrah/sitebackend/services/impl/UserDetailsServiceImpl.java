package com.ezrah.sitebackend.services.impl;

import com.ezrah.sitebackend.entities.User;
import com.ezrah.sitebackend.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        try {
            User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
            if (user != null) {
                return user;
            } else {
                throw new UsernameNotFoundException("Couldn't find username");
            }
        } catch (Exception e) {
            log.error("Database error", e);
            throw new UsernameNotFoundException("Database error");
        }
    }
}
