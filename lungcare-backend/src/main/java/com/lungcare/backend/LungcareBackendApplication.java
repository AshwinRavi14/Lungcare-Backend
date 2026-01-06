package com.lungcare.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lungcare.backend")
public class LungcareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LungcareBackendApplication.class, args);
	}

}
