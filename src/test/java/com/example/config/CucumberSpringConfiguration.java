package com.example.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.UserServiceApplication;
import org.springframework.boot.test.context.SpringBootTest;
// Import your main Spring Boot application class

@CucumberContextConfiguration
@SpringBootTest(classes = UserServiceApplication.class)
public class CucumberSpringConfiguration {
}


