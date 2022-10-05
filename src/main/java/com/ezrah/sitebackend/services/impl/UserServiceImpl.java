package com.ezrah.sitebackend.services.impl;

import com.ezrah.sitebackend.entities.Authority;
import com.ezrah.sitebackend.entities.User;
import com.ezrah.sitebackend.enums.UserAuthoritiesEnum;
import com.ezrah.sitebackend.repositories.AuthorityRepo;
import com.ezrah.sitebackend.repositories.UserRepo;
import com.ezrah.sitebackend.services.UserService;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(String username, String email, String password) {
        Assert.hasText(username, "username is empty");
        Assert.hasText(email, "email is empty");
        Assert.hasText(password, "password is empty");
        Assert.isTrue(EmailValidator.getInstance().isValid(email),"Invalid email");
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Authority regularAuthority = authorityRepo.findByAuthority(UserAuthoritiesEnum.REGULAR.toString());
        user.setAuthorities(Collections.singletonList(regularAuthority));
        return userRepo.save(user);
    }

    @Override
    public User getFromPrinciple(Principal principal) {
        return userRepo.findByUsernameOrEmail(principal.getName(), principal.getName());
    }
}
