package com.lszpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class LszhtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LszhtApplication.class, args);
	}
}
