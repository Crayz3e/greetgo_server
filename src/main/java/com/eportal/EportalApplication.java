package com.eportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
public class EportalApplication {
    public static void main(String[] args) {
        SpringApplication.run(EportalApplication.class, args);
    }
}
