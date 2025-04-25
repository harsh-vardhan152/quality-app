package com.harshvardhan.quality_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class QualityApp {

    public static void main(String[] args) {
        SpringApplication.run(QualityApp.class, args);
    }

}
