package com.estudo.CadastroDeNinjas.Ninjas.security;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.estudo.CadastroDeNinjas.Ninjas.model.UserAccount;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String jwtSecretBase64;

    @Value("${security.jwt.expirationMillis:3600000}")
    private long expirationMillis;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretBase64));
    }

    public String generateToken(UserAccount user){
        String roles = user.getRoles().stream()
            .map(Enum::name)
            .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token){
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        try {
            parseClaims(token);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    private Claims parseClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}


