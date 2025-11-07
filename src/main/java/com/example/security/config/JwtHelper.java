package com.example.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDATOR = 5*60*60;
    public static String secret = "bXlTdXBlclNlY3JldEtleU15U3VwZXJTZWNyZXRLZXk=";

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToke(claims,userDetails.getUsername());
    }

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> getSubject) {
        final Claims claims = getAllClaimsFromToken(token);
        return getSubject.apply(claims);
    }

    private String doGenerateToke(Map<String, Object> claims, String username) {
        return builder().setClaims(claims)
                .setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDATOR*1000))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public Claims getAllClaimsFromToken(String token) {

//        Jwts.parser()
//                .verifyWith(getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        Key key =  Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        System.out.println(key);
        return key;
    }


}
