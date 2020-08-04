package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repositories")
@EnableScheduling
public class ProducingWebService1Application  {

	public static void main(String[] args) {
		
		SpringApplication.run(ProducingWebService1Application.class, args);
	}

}
