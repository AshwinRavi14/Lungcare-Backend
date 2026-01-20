package com.lungcare.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.lungcare.backend")
public class LungcareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LungcareBackendApplication.class, args);
	}

}
