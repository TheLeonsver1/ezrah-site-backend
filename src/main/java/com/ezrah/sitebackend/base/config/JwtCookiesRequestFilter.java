package com.ezrah.sitebackend.base.config;


import com.ezrah.sitebackend.base.common.JWTUtils;
import com.ezrah.sitebackend.users.User;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtCookiesRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getCookies() != null) {
            final Optional<Cookie> requestTokenCookie = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(jwtUtils.getAccessTokenCookieName())).findFirst();
            String username = null;
            String jwtToken = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenCookie.isPresent()) {
                jwtToken = requestTokenCookie.get().getValue();
                try {
                    username = jwtUtils.getUserNameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    logger.warn("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    logger.warn("JWT Token has expired");
                }
            } else {
                logger.warn("No access token cookie");
            }

            // Once we get the token validate it.
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtUtils.validateToken(jwtToken, (User) userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        chain.doFilter(request, response);
    }

}