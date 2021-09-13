package com.jubin.springboot.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;

import reactor.blockhound.BlockHound;

@SpringBootApplication
public class JubinSpringBootApplication {

	public static void main(String[] args) {
		// blocking code detect
		// BlockHound.install();

		// blocking code detect, allow Thymeleaf TemplateEngine.process method
		BlockHound.builder()
			.allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
			.install();

		SpringApplication.run(JubinSpringBootApplication.class, args);
	}

}
