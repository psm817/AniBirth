package com.cod.AniBirth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AniBirthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AniBirthApplication.class, args);
	}

}
