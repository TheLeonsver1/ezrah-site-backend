package com.ezrah.sitebackend.common;

import lombok.Builder;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;

@Component
public class CookieUtils {
    @Value("${cookie.domain}")
    String domain;

    public ResponseCookie createCookie(String name, String value, boolean isHttpOnly, boolean isSecure, int maxAge) {
        return ResponseCookie
                .from(name, value)
                .secure(isSecure)
                .httpOnly(isHttpOnly)
                .path("/")
                .maxAge(maxAge)
                .sameSite(SameSiteCookies.STRICT.getValue())
                .domain(domain)
                .build();
    }
}
