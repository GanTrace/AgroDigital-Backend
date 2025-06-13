package com.acme.agrodigitalbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class AgroDigitalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgroDigitalBackendApplication.class, args);
	}

}
