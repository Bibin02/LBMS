package com.project.lbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LbmsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LbmsApplication.class, args);

		context.containsBean("null");
	}

}
