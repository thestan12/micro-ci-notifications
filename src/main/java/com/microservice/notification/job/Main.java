package com.microservice.notification.job;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan({"com.microservice.notification.utils"})
@Configuration
public class Main {

	@Bean
	public ApplicationRunner runner() {
		return new Runner();
	}

	public static void main(final String[] args) {
		SpringApplication application = new SpringApplication(Main.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		System.exit(SpringApplication.exit(application.run(args)));
	}

}
