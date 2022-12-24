package com.ezrah.sitebackend.users;

import java.security.Principal;

public interface UserService {
    User registerUser(String username, String email, String password);

    User getFromPrinciple(Principal principal);

}
