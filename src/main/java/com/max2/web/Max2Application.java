package com.max2.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("")
@SpringBootApplication
public class Max2Application {

	public static void main(String[] args) {
		SpringApplication.run(Max2Application.class, args);
	}
}
