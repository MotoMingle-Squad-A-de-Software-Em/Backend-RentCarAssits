package com.motomingle.rentcarassits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RentcarassitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentcarassitsApplication.class, args);
    }

}
