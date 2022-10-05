package com.ezrah.sitebackend.objects;

import lombok.Data;

@Data
public class JWTRequest {
    String usernameOrEmail;
    String password;
}
