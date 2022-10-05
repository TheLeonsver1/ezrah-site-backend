package com.ezrah.sitebackend.services;

import com.ezrah.sitebackend.entities.User;

import java.security.Principal;

public interface UserService {
    User registerUser(String username, String email, String password);

    User getFromPrinciple(Principal principal);

}
