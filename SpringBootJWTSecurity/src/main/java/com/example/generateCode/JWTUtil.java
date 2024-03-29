package com.example.generateCode;

/*
    created by Atiye Mousavi
    Date: 1/1/2023
    Time: 8:34 PM
*/


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTUtil {

    // code to generate Token
    public static String generateToken(String subject, String secret_key) {

        return Jwts.builder()
                .setId("tk9931")
                .setSubject(subject)
                .setIssuer("ABC_Ltd")
                .setAudience("XYZ_Ltd")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret_key.getBytes()))
                .compact();
    }

    //code to get Claims
    public static Claims getClaims(String token, String secret_key) {

        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secret_key.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }


}