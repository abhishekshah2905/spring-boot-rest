package com.dsesolutions.welcometest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The Spring boot application entry point to trriger the application context
 * 
 * @author Abhishek Shah
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class WelcomeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelcomeTestApplication.class, args);
	}

}
