package com.ezrah.sitebackend.base.objects;

import lombok.Data;

@Data
public class JWTRequest {
    String usernameOrEmail;
    String password;
}
