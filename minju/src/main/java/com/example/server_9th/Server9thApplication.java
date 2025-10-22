package com.example.server_9th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Server9thApplication {

    public static void main(String[] args) {
        SpringApplication.run(Server9thApplication.class, args);
    }

}
