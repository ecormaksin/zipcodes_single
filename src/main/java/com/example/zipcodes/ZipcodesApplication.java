package com.example.zipcodes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZipcodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipcodesApplication.class, args);
	}

}
