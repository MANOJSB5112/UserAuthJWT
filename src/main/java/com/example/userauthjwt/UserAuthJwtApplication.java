package com.example.userauthjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserAuthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAuthJwtApplication.class, args);
    }

}
