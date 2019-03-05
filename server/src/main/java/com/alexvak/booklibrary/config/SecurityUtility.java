package com.alexvak.booklibrary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
class SecurityUtility {

    @Value("${secret.key:secret}")
    private String secret;

    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(secret.getBytes()));
    }

}
