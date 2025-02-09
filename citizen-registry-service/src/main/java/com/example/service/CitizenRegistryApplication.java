package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.service.repository")
@EntityScan(basePackages = "com.example.domain")
public class CitizenRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitizenRegistryApplication.class, args);
    }
}
