package com.ezrah.sitebackend.common;

import com.ezrah.sitebackend.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Getter
    @Value("${jwt.access.token.cookie.name}")
    private String accessTokenCookieName;

    public static final long ACCESS_TOKEN_VALIDITY_IN_DAYS = 5;

    //retrieve user id from jwt token
    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public long getAccessTokenValidityInMilliseconds(){
        return ChronoUnit.DAYS.getDuration().toMillis() * ACCESS_TOKEN_VALIDITY_IN_DAYS;
    }

    public long getAccessTokenValidityInSeconds(){
        return ChronoUnit.DAYS.getDuration().toSeconds() * ACCESS_TOKEN_VALIDITY_IN_DAYS;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + getAccessTokenValidityInMilliseconds()))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        //TODO: set issuer
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUserNameFromToken(token);
        //TODO: check issuer
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}