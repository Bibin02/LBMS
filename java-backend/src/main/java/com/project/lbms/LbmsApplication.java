package com.project.lbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.lbms.repository.SampleRepo;

@SpringBootApplication
public class LbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LbmsApplication.class, args);

		System.out.println(new SampleRepo().getUsers());
		
	}

}
