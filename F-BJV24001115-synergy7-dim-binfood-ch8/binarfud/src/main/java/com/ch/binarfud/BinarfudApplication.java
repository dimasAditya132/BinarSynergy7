package com.ch.binarfud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BinarfudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinarfudApplication.class, args);
	}

}
