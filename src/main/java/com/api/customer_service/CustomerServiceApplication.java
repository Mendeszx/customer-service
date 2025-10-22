package com.api.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {

		System.out.println("Abcdef1!".matches("\\\"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\\\\\d)(?=.*[!@#$%^&*()_+]).{8,}$\\\""));

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
