package com.cvr.sbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SBookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBookBackendApplication.class, args);
	}

}
