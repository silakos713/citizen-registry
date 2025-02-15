package com.example.citizenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.citizenservice", "com.example.citizenentities"})
@EntityScan(basePackages = {"com.example.citizenentities.model"})
@EnableJpaRepositories(basePackages = {"com.example.citizenservice.repository"})
public class CitizenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitizenServiceApplication.class, args);
    }
}
