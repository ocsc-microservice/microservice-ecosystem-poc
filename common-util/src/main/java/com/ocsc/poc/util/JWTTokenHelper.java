package com.ocsc.poc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JWTTokenHelper {

    private static String privateKey = "abcdefghijklmn";
    private static Integer tokenExpiryTime = 1800000;

    public static Map<String, ?> getClaimsByKey(String key, String token) {
        return JWT.decode(token)
                .getClaim(key)
                .asMap();
    }

    public static String createToken(String subject, String claimsKey, Map<String, ?> claims){
        return JWT.create()
                .withSubject(subject)
                .withIssuer("oracle")
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiryTime))
                .withClaim(claimsKey, claims)
                .sign(Algorithm.HMAC512(privateKey.getBytes()));
    }

    public static String createToken(String subject){
        return JWT.create()
                .withSubject(subject)
                .withIssuer("oracle")
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiryTime))
                .sign(Algorithm.HMAC512(privateKey.getBytes()));
    }

    public static String verifyToken(String token){
        return JWT.require(Algorithm.HMAC512(privateKey.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
