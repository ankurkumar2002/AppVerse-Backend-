package com.example.auth_service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private String secretKey = "8YV3pL9kLx8pD3V64bF6KL8Z2j5rF8S9dD9KvLs6Fx8=";

    @Autowired
    UserService userService;

    public String generateToken(UserDetails userDetails, boolean rememberMe){
        Map<String , Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()));

        long expirationTime = rememberMe ? 30L * 24 * 60 * 60 * 1000 : 30 * 60 * 1000; // 30 days or 30 minutes

        return Jwts.builder()
                   .claims()
                   .add(claims)
                   .subject(userDetails.getUsername())
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + expirationTime))
                   .and()
                   .signWith(getKey())
                   .compact();
    }
    

    // public String generateToken(UserDetails userDetails) {
    //     Map<String, Object> claims = new HashMap<>();
        
    //     return Jwts.builder()
    //                .setClaims(claims)
    //                .setSubject(userDetails.getUsername())
    //                .setIssuedAt(new Date(System.currentTimeMillis()))
    //                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
    //                .signWith(getKey())
    //                .compact();
    // }

    

    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                   .verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public List<String> extractRoles(String token) {
        List<?> rawList = extractClaim(token, claims -> claims.get("roles", List.class));
    return rawList.stream()
                  .filter(item -> item instanceof String)
                  .map(item -> (String) item)
                  .collect(Collectors.toList());
    }


}
