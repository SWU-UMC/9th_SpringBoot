package com.example.leeseo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class leeseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(leeseoApplication.class, args);
	}

}

