package com.max2.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@ImportResource("classpath:spring-config.xml")
@EntityScan("com.max2.model")
@EnableAsync
@SpringBootApplication
public class Max2Application {

	public static void main(String[] args) {
		SpringApplication.run(Max2Application.class, args);
	}
}
