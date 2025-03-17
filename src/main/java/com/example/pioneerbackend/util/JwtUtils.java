package com.example.pioneerbackend.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class JwtUtils {
    public static String encodePassword(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
