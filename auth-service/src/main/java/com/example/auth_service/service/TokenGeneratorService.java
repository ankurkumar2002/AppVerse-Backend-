package com.example.auth_service.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorService {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
