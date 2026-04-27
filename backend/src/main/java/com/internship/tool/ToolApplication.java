package com.internship.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication // Main Spring Boot configuration annotation
@EnableJpaAuditing // Enables JPA auditing (for createdAt & updatedAt)
@EnableCaching // Enables caching in application
public class ToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolApplication.class, args); // Starts the application
    }
}