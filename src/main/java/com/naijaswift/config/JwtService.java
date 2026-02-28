package com.naijaswift.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
     @Value("${app.jwt.secret}")
     private String secretKey;
      public String generateToken(Map<String,Object> extraClaims, String userName){
        return Jwts.builder()
               .setClaims(extraClaims)
               .setSubject(userName)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
               .signWith(getSignKey(),SignatureAlgorithm.HS256)
               .compact();
      }
      private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
      }
    
}
    
