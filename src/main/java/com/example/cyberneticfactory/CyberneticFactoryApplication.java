package com.example.cyberneticfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CyberneticFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyberneticFactoryApplication.class, args);
    }

}
