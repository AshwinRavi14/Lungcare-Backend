package com.lungcare.backend.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashGenerator {
    public static void main(String[] args)
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("BCrypt hash for pass123");
        System.out.println(encoder.encode("pass123"));
    }
}
