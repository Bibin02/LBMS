package com.project.lbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.project.lbms")
public class LbmsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(LbmsApplication.class, args);
		
	}

}
