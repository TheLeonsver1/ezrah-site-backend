package com.ezrah.sitebackend.controllers;

import com.ezrah.sitebackend.common.CookieUtils;
import com.ezrah.sitebackend.common.JWTUtils;
import com.ezrah.sitebackend.entities.User;
import com.ezrah.sitebackend.objects.JWTRequest;
import com.ezrah.sitebackend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final JWTUtils jwtUtils;

    private final UserService userService;

    private final CookieUtils cookieUtils;

    @PostMapping("/register")
    private ResponseEntity<Boolean> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            User result = userService.registerUser(registerUserRequest.username, registerUserRequest.email, registerUserRequest.password);
            if (result != null) {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            log.error("Error while registering user", e);
        }
        return ResponseEntity.internalServerError().build();
    }

    record RegisterUserRequest(@NotBlank String username, @NotBlank @Email String email,
                               @NotNull @NotBlank @Min(8) String password) {
    }

    @PostMapping("/login")
    private ResponseEntity<Void> login(HttpServletRequest request,HttpServletResponse response, @RequestBody JWTRequest jwtRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsernameOrEmail(), jwtRequest.getPassword()));
            response.addHeader(HttpHeaders.SET_COOKIE, cookieUtils.createCookie(jwtUtils.getAccessTokenCookieName(), jwtUtils.generateToken((User) auth.getPrincipal()),true, request.isSecure(), (int) jwtUtils.getAccessTokenValidityInSeconds()).toString());
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("Failed authentication", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refreshToken")
    private boolean refreshToken() {
        return true;
    }


}
